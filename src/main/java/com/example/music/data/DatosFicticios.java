package com.example.music.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.example.music.model.Cancion;
import com.example.music.model.Interaccion;
import com.example.music.model.Usuario;

public class DatosFicticios {
    private static List<Cancion> canciones = new ArrayList<>();
    private static List<Usuario> usuariosBase = new ArrayList<>();
    private static Map<Long, List<Interaccion>> historialGlobal = new HashMap<>();

    static {
        // ========== TODAS LAS CANCIONES CON AUDIO LOCAL ==========
        // Índice de IDs comenzando en 1
        // Formato: id, título, artista, género, popularidad, energía, bailabilidad, ruta, portadaUrl

        // 1. poster-boy.mp3
        canciones.add(new Cancion(1L, "Poster Boy", "2hollis", "Pop", 82, 0.70, 0.65, "/audios/poster-boy.mp3", "https://picsum.photos/id/100/200"));
        
        // 2. MONTAGEM-HIKARI.mp3
        canciones.add(new Cancion(2L, "MONTAGEM HIKARI", "BellyJay", "Electrónica", 88, 0.92, 0.88, "/audios/MONTAGEM-HIKARI.mp3", "https://picsum.photos/id/101/200"));
        
        // 3. Calvin Harris-Outside(Lyrics).mp3
        canciones.add(new Cancion(3L, "Outside", "Calvin Harris ft. Ellie Goulding", "Pop", 94, 0.78, 0.72, "/audios/Calvin Harris-Outside(Lyrics).mp3", "https://picsum.photos/id/102/200"));
        
        // 4. The Neighbourhood-Softcore.mp3
        canciones.add(new Cancion(4L, "Softcore", "The Neighbourhood", "Alternativo", 84, 0.60, 0.50, "/audios/The Neighbourhood-Softcore.mp3", "https://picsum.photos/id/103/200"));
        
        // 5. 2000.mp3
        canciones.add(new Cancion(5L, "2000", "Desconocido", "Electrónica", 75, 0.80, 0.75, "/audios/2000.mp3", "https://picsum.photos/id/104/200"));
        
        // 6. LUMIX _ KXRSED-MONTAGEM-NOCHE.mp3
        canciones.add(new Cancion(6L, "MONTAGEM NOCHE", "LUMIX, KXRSED", "Electrónica", 87, 0.90, 0.85, "/audios/LUMIX _ KXRSED-MONTAGEM-NOCHE.mp3", "https://picsum.photos/id/105/200"));
        
        // 7. NUEKI_TOLCHONOV-SO-TIRED.mp3
        canciones.add(new Cancion(7L, "SO TIRED", "NUEKI, TOLCHONOV", "Trap", 86, 0.88, 0.85, "/audios/NUEKI_TOLCHONOV-SO-TIRED.mp3", "https://picsum.photos/id/106/200"));
        
        // 8. Selena-Gomez-Love-You-Like-a-Love-Song-(Lyrics).mp3
        canciones.add(new Cancion(8L, "Love You Like a Love Song", "Selena Gomez", "Pop", 89, 0.72, 0.78, "/audios/Selena-Gomez-Love-You-Like-a-Love-Song-(Lyrics).mp3", "https://picsum.photos/id/107/200"));
        
        // 9. you-broke-my-heart-kobzx2z_Myla.mp3
        canciones.add(new Cancion(9L, "you broke my heart", "kobzx2z, Myla", "Trap", 83, 0.85, 0.80, "/audios/you-broke-my-heart-kobzx2z_Myla.mp3", "https://picsum.photos/id/108/200"));
        
        // 10. Enrique Iglesias-Bailandoft.Descemer-Bueno_Gente-De-Zona.mp3
        canciones.add(new Cancion(10L, "Bailando", "Enrique Iglesias ft. Descemer Bueno, Gente De Zona", "Latino", 93, 0.82, 0.90, "/audios/Enrique Iglesias-Bailandoft.Descemer-Bueno_Gente-De-Zona.mp3", "https://picsum.photos/id/109/200"));
        
        // 11. Porque-estoy-enamorada-ahora-I-dont-know-Erika__Sub-espa ol.mp3
        canciones.add(new Cancion(11L, "Porque estoy enamorada ahora?", "Erika", "Pop", 82, 0.68, 0.70, "/audios/Porque-estoy-enamorada-ahora-I-dont-know-Erika__Sub-español.mp3", "https://picsum.photos/id/110/200"));
        
        // 12. Black-Clover-OP-10-Full-Vickeblanka-Black-Catcher-Sub-Espa ol _HD.mp3
        canciones.add(new Cancion(12L, "Black Catcher", "Vickeblanka", "Anime", 91, 0.88, 0.75, "/audios/Black-Clover-OP-10-Full-Vickeblanka-Black-Catcher-Sub-Español _HD.mp3", "https://picsum.photos/id/111/200"));
        
        // 13. Attack-on-Titan-Final-Season-Ending-2-Akuma-no.mp3
        canciones.add(new Cancion(13L, "Akuma no Ko", "Ai Higuchi", "Anime", 96, 0.85, 0.70, "/audios/Attack-on-Titan-Final-Season-Ending-2-Akuma-no.mp3", "https://picsum.photos/id/112/200"));
        
        // 14. Attack-on-Titan-Season-2-Official-Opening-Song-Shinzou-wo-Sasageyo-by-Linked-Horizon.mp3
        canciones.add(new Cancion(14L, "Shinzou wo Sasageyo", "Linked Horizon", "Anime", 97, 0.95, 0.85, "/audios/Attack-on-Titan-Season-2-Official-Opening-Song-Shinzou-wo-Sasageyo-by-Linked-Horizon.mp3", "https://picsum.photos/id/113/200"));
        
        // 15. TV??????????Season-3-Part-1???????OP-YOSHIKI-feat.HYDE.mp3 (asumo Red Swan)
        canciones.add(new Cancion(15L, "Red Swan", "YOSHIKI feat. HYDE", "Anime", 94, 0.80, 0.72, "/audios/TVアニメ「進撃の巨人」Season-3-Part-1ノンクレジットOP-YOSHIKI-feat.HYDE.mp3", "https://picsum.photos/id/114/200"));
        
        // 16. Attack-on-Titan-Final-Season-Opening-2-The-Rumbling.mp3
        canciones.add(new Cancion(16L, "The Rumbling", "SiM", "Anime", 98, 0.92, 0.88, "/audios/Attack-on-Titan-Final-Season-Opening-2-The-Rumbling.mp3", "https://picsum.photos/id/115/200"));
        
        // 17. blackbear-hot girl bummer-This-that-hot-girl-bummer-anthem.mp3
        canciones.add(new Cancion(17L, "hot girl bummer", "blackbear", "Pop", 88, 0.80, 0.75, "/audios/blackbear-hot girl bummer-This-that-hot-girl-bummer-anthem.mp3", "https://picsum.photos/id/116/200"));
        
        // 18. Lost-Sky-Fearless-(Lyrics)-pt.II.mp3
        canciones.add(new Cancion(18L, "Fearless", "Lost Sky ft. Chris Linton", "Electrónica", 89, 0.86, 0.78, "/audios/Lost-Sky-Fearless-(Lyrics)-pt.II.mp3", "https://picsum.photos/id/117/200"));
        
        // 19. Nightcore-How-Do-You-Do.mp3
        canciones.add(new Cancion(19L, "How Do You Do", "Nightcore", "Electrónica", 85, 0.90, 0.88, "/audios/Nightcore-How-Do-You-Do.mp3", "https://picsum.photos/id/118/200"));
        
        // 20. TV???????? ???????????OP????-ClariS?ALIVE.mp3 (asumo Lycoris Recoil OP - ALIVE)
        canciones.add(new Cancion(20L, "Lycoris Recoil", "OP", "Anime", 90, 0.82, 0.75, "/audios/Lycoris-recoil.mp3", "https://picsum.photos/id/119/200"));
        
        // 21. Alan-Walker-Darkside-(Lyrics)-ft.Au-Ra-and-Tomine-Harket.mp3
        canciones.add(new Cancion(21L, "Darkside", "Alan Walker ft. Au/Ra, Tomine Harket", "Electrónica", 95, 0.88, 0.72, "/audios/Alan-Walker-Darkside-(Lyrics)-ft.Au-Ra-and-Tomine-Harket.mp3", "https://picsum.photos/id/120/200"));
        
        // 22. Alan-Walker-Alone-(Lyrics).mp3
        canciones.add(new Cancion(22L, "Alone", "Alan Walker", "Electrónica", 97, 0.85, 0.75, "/audios/Alan-Walker-Alone-(Lyrics).mp3", "https://picsum.photos/id/121/200"));
        
        // 23. Alan-Walker-Sabrina-Carpenter-Farruko--On-My-Way.mp3
        canciones.add(new Cancion(23L, "On My Way", "Alan Walker, Sabrina Carpenter, Farruko", "Electrónica", 93, 0.84, 0.78, "/audios/Alan-Walker-Sabrina-Carpenter-Farruko--On-My-Way.mp3", "https://picsum.photos/id/122/200"));
        
        // 24. Alan-Walker-Faded.mp3
        canciones.add(new Cancion(24L, "Faded", "Alan Walker", "Electrónica", 99, 0.80, 0.70, "/audios/Alan-Walker-Faded.mp3", "https://picsum.photos/id/123/200"));
        
        // 25. Alan-Walker-K-391-Tungevaag-Mangoo-PLAY-.mp3
        canciones.add(new Cancion(25L, "PLAY", "Alan Walker, K-391, Tungevaag, Mangoo", "Electrónica", 92, 0.86, 0.80, "/audios/Alan-Walker-K-391-Tungevaag-Mangoo-PLAY-.mp3", "https://picsum.photos/id/124/200"));
        
        // 26. LP-Lost-On-You-.mp3
        canciones.add(new Cancion(26L, "Lost On You", "LP", "Alternativo", 90, 0.65, 0.55, "/audios/LP-Lost-On-You-.mp3", "https://picsum.photos/id/125/200"));
        
        // 27. Olly-Murs-That Girl.mp3
        canciones.add(new Cancion(27L, "That Girl", "Olly Murs", "Pop", 87, 0.72, 0.68, "/audios/Olly-Murs-That Girl.mp3", "https://picsum.photos/id/126/200"));
        
        // 28. ??????? _ ???? ????SV.mp3 (nombre ilegible, lo dejamos como "Canción Anime")
        canciones.add(new Cancion(28L, "Canción Anime", "Artista Desconocido", "Anime", 80, 0.78, 0.70, "/audios/??????? _ ???? ????SV.mp3", "https://picsum.photos/id/127/200"));
        
        // 29. Shingeki-No-Kyojin-2-Opening-Sub-Espa ol-Shinzou-wo-Sasageyo.mp4 (video, pero lo tratamos como audio)
        canciones.add(new Cancion(29L, "Shinzou wo Sasageyo (versión video)", "Linked Horizon", "Anime", 97, 0.95, 0.85, "/audios/Shingeki-No-Kyojin-2-Opening-Sub-Espa ol-Shinzou-wo-Sasageyo.mp4", "https://picsum.photos/id/128/200"));
        
        // 30. MIIA-Dynasty-(Lyrics).mp3
        canciones.add(new Cancion(30L, "Dynasty", "MIIA", "Electrónica", 86, 0.78, 0.70, "/audios/MIIA-Dynasty-(Lyrics).mp3", "https://picsum.photos/id/129/200"));
        
        // 31. chris-grey-let-the-world-burn-sub-espa ol.mp3
        canciones.add(new Cancion(31L, "let the world burn", "chris grey", "Alternativo", 84, 0.68, 0.62, "/audios/chris-grey-let-the-world-burn-sub-espa ol.mp3", "https://picsum.photos/id/130/200"));
        
        // 32. Love-is-a-beautiful-Pain-Endless-Tears.mp3
        canciones.add(new Cancion(32L, "Love is a beautiful Pain", "Endless Tears", "Balada", 82, 0.60, 0.55, "/audios/Love-is-a-beautiful-Pain-Endless-Tears.mp3", "https://picsum.photos/id/131/200"));
        
        // 33. James-Arthur-Car_s-Outside-(tiktok-version)-Sub-Espa ol-Lyrics-oh-oh-oh.mp3
        canciones.add(new Cancion(33L, "Car's Outside", "James Arthur", "Pop", 88, 0.72, 0.60, "/audios/James-Arthur-Car_s-Outside-(tiktok-version)-Sub-Espa ol-Lyrics-oh-oh-oh.mp3", "https://picsum.photos/id/132/200"));
        
        // 34. Legends-Never-Die-(Lyrics)-Ft.Against-The-Current.mp3
        canciones.add(new Cancion(34L, "Legends Never Die", "Against The Current", "Electrónica", 98, 0.92, 0.80, "/audios/Legends-Never-Die-(Lyrics)-Ft.Against-The-Current.mp3", "https://picsum.photos/id/133/200"));
        
        // 35. TheFatRat-Slaydit-Anjulie-Stronger-Monstercat-Release.mp3
        canciones.add(new Cancion(35L, "Stronger", "TheFatRat, Slaydit, Anjulie", "Electrónica", 91, 0.88, 0.82, "/audios/TheFatRat-Slaydit-Anjulie-Stronger-Monstercat-Release.mp3", "https://picsum.photos/id/134/200"));
        
        // 36. NEFFEX-Careless-Sub-Espa ol.mp3
        canciones.add(new Cancion(36L, "Careless", "NEFFEX", "Rock", 85, 0.78, 0.70, "/audios/NEFFEX-Careless-Sub-Espa ol.mp3", "https://picsum.photos/id/135/200"));
        
        // 37. Nightcore-Shameless-(Camila-Cabello)-(Lyrics).m4a
        canciones.add(new Cancion(37L, "Shameless", "Camila Cabello (Nightcore)", "Pop", 86, 0.82, 0.78, "/audios/Nightcore-Shameless-_Camila-Cabello_-_Lyrics_.mp3", "https://picsum.photos/id/136/200"));
        
        // 38. Sea-Of-Problems.mp3
        canciones.add(new Cancion(38L, "Sea Of Problems", "GLICHERY", "Electrónica", 80, 0.86, 0.80, "/audios/Sea-Of-Problems.mp3", "https://picsum.photos/id/137/200"));
        
        // 39. Alex-Ponce-Plan.mp3
        canciones.add(new Cancion(39L, "Plan", "Alex Ponce", "Latino", 83, 0.72, 0.78, "/audios/Alex-Ponce-Plan.mp3", "https://picsum.photos/id/138/200"));
        
        // 40. Alec-Benjamin-Let-Me-Down-Slowly(Lyrics).mp3
        canciones.add(new Cancion(40L, "Let Me Down Slowly", "Alec Benjamin", "Pop", 90, 0.68, 0.70, "/audios/Alec-Benjamin-Let-Me-Down-Slowly(Lyrics).mp3", "https://picsum.photos/id/139/200"));
        
        // 41. Filter-Transition.mp3
        canciones.add(new Cancion(41L, "Filter Transition", "TikTok", "Electrónica", 78, 0.75, 0.80, "/audios/Filter-Transition.mp3", "https://picsum.photos/id/140/200"));
        
        // 42. Hymn-for-the-Weekend-Studio-Audio.mp3
        canciones.add(new Cancion(42L, "Hymn for the Weekend", "Coldplay", "Rock", 92, 0.70, 0.65, "/audios/Hymn-for-the-Weekend-Studio-Audio.mp3", "https://picsum.photos/id/141/200"));
        
        // 43. Impossible-I-Am-King-.mp3
        canciones.add(new Cancion(43L, "Impossible", "I Am King", "Rock", 83, 0.80, 0.55, "/audios/Impossible-I-Am-King-.mp3", "https://picsum.photos/id/142/200"));
        
        // 44. Meg-Dia-Monster.mp3
        canciones.add(new Cancion(44L, "Monster (DotEXE Remix)", "Meg & Dia", "Electrónica", 86, 0.90, 0.80, "/audios/Meg-Dia-Monster.mp3", "https://picsum.photos/id/143/200"));
        
        // 45. Nightcore-Thunder-(Lyrics).mp3
        canciones.add(new Cancion(45L, "Thunder", "Gabry Ponte, LUM!X, Prezioso", "Electrónica", 92, 0.94, 0.88, "/audios/Nightcore-Thunder-(Lyrics).mp3", "https://picsum.photos/id/144/200"));
        
        // 46. Quedate-Algo-me-gusta-de-ti-Bizarrap-Ft-Quevedo-Ft-Wisin-Yandel.mp3
        canciones.add(new Cancion(46L, "Quedate x Algo me gusta de ti", "Bizarrap ft. Quevedo, Wisin & Yandel", "Latino", 91, 0.85, 0.88, "/audios/Quedate-Algo-me-gusta-de-ti-Bizarrap-Ft-Quevedo-Ft-Wisin-Yandel.mp3", "https://picsum.photos/id/145/200"));
        
        // 47. sapientdream-past-lives.mp3
        canciones.add(new Cancion(47L, "past lives", "sapientdream", "Electrónica", 88, 0.65, 0.55, "/audios/sapientdream-past-lives.mp3", "https://picsum.photos/id/146/200"));
        
        // 48. Slander-Feat.Dylan-Matthew-Love-is-Gone.mp3
        canciones.add(new Cancion(48L, "Love is Gone", "Slander ft. Dylan Matthew", "Electrónica", 87, 0.75, 0.70, "/audios/Slander-Feat.Dylan-Matthew-Love-is-Gone.mp3", "https://picsum.photos/id/147/200"));
        
        // 49. Zzoilo-Aitana-Mon-Amour-Remix.mp3
        canciones.add(new Cancion(49L, "Mon Amour Remix", "Zzoilo, Aitana", "Latino", 91, 0.85, 0.88, "/audios/Zzoilo-Aitana-Mon-Amour-Remix.mp3", "https://picsum.photos/id/148/200"));
        
        // Puedes seguir agregando más si tienes (hasta el 50 o más), la estructura es la misma.

        // ========== USUARIOS BASE ==========
        // 5 usuarios predefinidos para pruebas
        for (int i = 1; i <= 5; i++) {
            Usuario u = new Usuario((long)i, "user" + i, "pass" + i, "Usuario " + i, 18 + i);
            usuariosBase.add(u);
        }

        // ========== HISTORIAL ALEATORIO PARA USUARIOS BASE ==========
        Random rand = new Random(42); // Semilla fija para reproducibilidad
        for (Usuario u : usuariosBase) {
            List<Interaccion> interacciones = new ArrayList<>();
            int num = 15 + rand.nextInt(15); // entre 15 y 30 interacciones
            Set<Long> usadas = new HashSet<>();
            for (int j = 0; j < num; j++) {
                Cancion c = canciones.get(rand.nextInt(canciones.size()));
                if (!usadas.add(c.getId())) continue;
                boolean like = rand.nextDouble() > 0.25; // 75% de likes
                interacciones.add(new Interaccion(u.getId(), c.getId(), like, new Date()));
            }
            historialGlobal.put(u.getId(), interacciones);
            u.setHistorial(interacciones);
        }
    }

    // ========== MÉTODOS PÚBLICOS ==========
    public static List<Cancion> getCanciones() {
        return canciones;
    }

    public static List<Usuario> getUsuarios() {
        return usuariosBase;
    }

    public static Map<Long, List<Interaccion>> getHistorial() {
        return historialGlobal;
    }

    public static void inicializarHistorialUsuario(Long userId) {
        historialGlobal.putIfAbsent(userId, new ArrayList<>());
    }

    public static void agregarInteraccion(Long userId, Interaccion inter) {
        historialGlobal.computeIfAbsent(userId, k -> new ArrayList<>()).add(inter);
        for (Usuario u : usuariosBase) {
            if (u.getId().equals(userId)) {
                u.getHistorial().add(inter);
                break;
            }
        }
    }
}