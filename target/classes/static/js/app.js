// ==================== VARIABLES GLOBALES ====================
let currentUser = null;
let todasCanciones = [];
let currentYoutubePlayer = null;

// ==================== FUNCIONES AUXILIARES ====================
async function fetchJson(url, options = {}) {
    const res = await fetch(url, options);
    if (!res.ok) {
        const text = await res.text();
        throw new Error(`HTTP ${res.status}: ${text.substring(0, 100)}`);
    }
    const contentType = res.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
        return await res.json();
    } else {
        return await res.text();
    }
}

function mostrarLogin() {
    document.getElementById('loginContainer').classList.remove('hidden');
    document.getElementById('registroContainer').classList.add('hidden');
    document.getElementById('appContainer').classList.add('hidden');
    document.getElementById('loginError').innerText = '';
}

function mostrarRegistro() {
    document.getElementById('loginContainer').classList.add('hidden');
    document.getElementById('registroContainer').classList.remove('hidden');
    document.getElementById('regError').innerText = '';
}

// ==================== LOGIN / REGISTRO ====================
async function login() {
    const username = document.getElementById('loginUsername').value.trim();
    const password = document.getElementById('loginPassword').value.trim();
    if (!username || !password) {
        document.getElementById('loginError').innerText = 'Complete todos los campos';
        return;
    }
    try {
        const result = await fetchJson('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        if (result === 'ok') {
            await cargarUsuarioActual();  // esto redirige inmediatamente
        } else {
            document.getElementById('loginError').innerText = 'Usuario o contraseña incorrectos';
        }
    } catch(e) {
        document.getElementById('loginError').innerText = 'Error de conexión: ' + e.message;
    }
}

async function registrar() {
    const username = document.getElementById('regUsername').value.trim();
    const password = document.getElementById('regPassword').value.trim();
    if (!username || !password) {
        document.getElementById('regError').innerText = 'Complete todos los campos';
        return;
    }
    try {
        const result = await fetchJson('/api/registro', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        if (result === 'ok') {
            await cargarUsuarioActual();
        } else {
            document.getElementById('regError').innerText = 'El usuario ya existe';
        }
    } catch(e) {
        document.getElementById('regError').innerText = 'Error de conexión: ' + e.message;
    }
}

async function logout() {
    try {
        await fetch('/api/logout', { method: 'POST' });
    } catch(e) {}
    if (currentYoutubePlayer) {
        currentYoutubePlayer.destroy();
        currentYoutubePlayer = null;
    }
    currentUser = null;
    document.getElementById('appContainer').classList.add('hidden');
    document.getElementById('floatingPlayer').classList.add('hidden');
    mostrarLogin();
}

async function cargarUsuarioActual() {
    try {
        const user = await fetchJson('/api/usuario-actual');
        if (user && user.id) {
            currentUser = user;
            document.getElementById('userName').innerText = user.nombre;
            document.getElementById('loginContainer').classList.add('hidden');
            document.getElementById('registroContainer').classList.add('hidden');
            document.getElementById('appContainer').classList.remove('hidden');
            await cargarTodasCanciones();
            await cargarRecomendaciones();
            mostrarCatalogoGeneral();
        } else {
            mostrarLogin();
        }
    } catch(e) {
        console.error('Error cargando usuario:', e);
        mostrarLogin();
    }
}

// ==================== CARGA DE DATOS ====================
async function cargarTodasCanciones() {
    try {
        todasCanciones = await fetchJson('/api/canciones/todas') || [];
        console.log('Catálogo cargado:', todasCanciones.length);
    } catch(e) {
        console.error('Error cargando catálogo:', e);
        todasCanciones = [];
    }
}

async function cargarRecomendaciones() {
    try {
        const sim = await fetchJson('/api/recomendar/similitud');
        renderCards(sim || [], 'similitudCards');
        const arbol = await fetchJson('/api/recomendar/arbol');
        renderCards(arbol || [], 'arbolCards');
    } catch(e) {
        console.error('Error en recomendaciones:', e);
        renderCards([], 'similitudCards');
        renderCards([], 'arbolCards');
    }
}

function mostrarCatalogoGeneral() {
    const container = document.getElementById('catalogoCards');
    if (container && todasCanciones.length) {
        renderCards(todasCanciones.slice(0, 20), 'catalogoCards');
    }
}

function renderCards(canciones, containerId) {
    const container = document.getElementById(containerId);
    if (!container) return;
    if (!canciones.length) {
        container.innerHTML = '<div class="loading">No hay canciones aún. ¡Da like para recibir recomendaciones!</div>';
        return;
    }
    container.innerHTML = canciones.map(c => `
        <div class="card" data-id="${c.id}">
            <img src="${c.portadaUrl || 'https://picsum.photos/id/100/200'}" 
                 onerror="this.src='https://picsum.photos/id/100/200'">
            <div class="card-info">
                <h3>${escapeHtml(c.titulo)}</h3>
                <p>${escapeHtml(c.artista)} • ${escapeHtml(c.genero)}</p>
                <button class="like-btn" data-id="${c.id}">❤️ Like</button>
            </div>
        </div>
    `).join('');

    // Asignar eventos después de insertar
    container.querySelectorAll('.card').forEach(card => {
        const id = parseInt(card.dataset.id);
        card.addEventListener('click', (e) => {
            if (!e.target.classList.contains('like-btn')) {
                reproducirCancion(id);
            }
        });
    });
    container.querySelectorAll('.like-btn').forEach(btn => {
        const id = parseInt(btn.dataset.id);
        btn.addEventListener('click', (e) => {
            e.stopPropagation();
            darLike(id);
        });
    });
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>]/g, function(m) {
        if (m === '&') return '&amp;';
        if (m === '<') return '&lt;';
        if (m === '>') return '&gt;';
        return m;
    });
}

async function darLike(id) {
    try {
        await fetch(`/api/like/${id}`, { method: 'POST' });
        await cargarRecomendaciones();
    } catch(e) {
        console.error('Error al dar like:', e);
    }
}

// ==================== REPRODUCCIÓN (SOLO AUDIO, SIN VIDEO) ====================
function reproducirCancion(id) {
    const cancion = todasCanciones.find(c => c.id === id);
    if (!cancion) return;

    // Mostrar reproductor flotante
    const playerDiv = document.getElementById('floatingPlayer');
    playerDiv.classList.remove('hidden');

    // Actualizar info
    document.getElementById('playerTitle').innerText = cancion.titulo;
    document.getElementById('playerArtist').innerText = cancion.artista;
    document.getElementById('playerImg').src = cancion.portadaUrl || 'https://picsum.photos/id/100/50';

    const audioEl = document.getElementById('audioPlayer');
    const youtubeContainer = document.getElementById('youtubeAudioContainer');

    // Detener reproducción anterior
    if (currentYoutubePlayer) {
        currentYoutubePlayer.destroy();
        currentYoutubePlayer = null;
    }
    audioEl.pause();
    audioEl.src = '';
    youtubeContainer.innerHTML = '';

    if (cancion.youtubeVideoId && cancion.youtubeVideoId.trim() !== '') {
        // Reproductor de YouTube oculto (solo audio)
        audioEl.style.display = 'none';
        youtubeContainer.innerHTML = '<div id="youtubeAudioPlayer"></div>';
        const playerDivYt = document.getElementById('youtubeAudioPlayer');
        currentYoutubePlayer = new YT.Player('youtubeAudioPlayer', {
            height: '1',
            width: '1',
            videoId: cancion.youtubeVideoId,
            playerVars: {
                autoplay: 1,
                controls: 0,
                modestbranding: 1,
                rel: 0,
                showinfo: 0,
                iv_load_policy: 3
            },
            events: {
                onReady: (event) => event.target.playVideo(),
                onError: (err) => {
                    console.error('Error de YouTube:', err);
                    audioEl.style.display = 'block';
                    youtubeContainer.innerHTML = '';
                    currentYoutubePlayer = null;
                    if (cancion.urlPreview) {
                        audioEl.src = cancion.urlPreview;
                        audioEl.play();
                    }
                }
            }
        });
    } else if (cancion.urlPreview) {
        audioEl.style.display = 'block';
        audioEl.src = cancion.urlPreview;
        audioEl.play().catch(e => console.log('Error reproduciendo audio:', e));
    } else {
        console.log('No hay fuente de audio');
    }
}

// ==================== PLAYLIST RECURSIVA ====================
async function generarPlaylistRecursiva() {
    const container = document.getElementById('recursivoCards');
    container.innerHTML = '<div class="loading">Generando playlist recursiva...</div>';
    if (todasCanciones.length === 0) await cargarTodasCanciones();
    if (!todasCanciones.length) {
        container.innerHTML = '<div class="loading">No hay canciones disponibles</div>';
        return;
    }
    const semilla = todasCanciones[0].id;
    try {
        const lista = await fetchJson(`/api/playlist/recursiva/${semilla}/5`);
        if (lista && lista.length) renderCards(lista, 'recursivoCards');
        else container.innerHTML = '<div class="loading">No se pudo generar playlist</div>';
    } catch(e) {
        container.innerHTML = '<div class="loading">Error al generar playlist</div>';
    }
}

// ==================== EVENTOS ====================
document.getElementById('btnLogin').addEventListener('click', login);
document.getElementById('btnShowRegistro').addEventListener('click', mostrarRegistro);
document.getElementById('btnRegistrar').addEventListener('click', registrar);
document.getElementById('btnBackLogin').addEventListener('click', mostrarLogin);
document.getElementById('btnLogout').addEventListener('click', logout);
document.getElementById('btnRecursivo').addEventListener('click', generarPlaylistRecursiva);

// Inicializar al cargar
window.onload = () => {
    cargarUsuarioActual();
};