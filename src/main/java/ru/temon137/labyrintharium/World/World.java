package ru.temon137.labyrintharium.World;


import ru.temon137.labyrintharium.Render.RenderThread;
import ru.temon137.labyrintharium.World.GameObjects.Beings.Being;
import ru.temon137.labyrintharium.World.GameObjects.Beings.Gamer;
import ru.temon137.labyrintharium.World.GameObjects.Blocks.Block;
import ru.temon137.labyrintharium.World.GameObjects.Coord;
import ru.temon137.labyrintharium.World.GameObjects.GameObject;


public class World {
    private static Map<Block> backgroundsMap = new Map<>();
    private static Map<Block> blocksMap = new Map<>();
    private static Map<Being> beingsMap = new Map<>();
    //private static Map<Decal> decalsMap;

    private static RenderThread renderThread;

    private static Gamer gamer;


    public static void initialize() {
        backgroundsMap = new Map<>();
        blocksMap = new Map<>();
        beingsMap = new Map<>();

        if (renderThread != null)
            renderThread.stopRender();
        (renderThread = new RenderThread()).start();
    }

    public static Map<Block> getBackgroundsMap() {
        return backgroundsMap;
    }

    public static Map<Block> getBlocksMap() {
        return blocksMap;
    }

    public static Map<Being> getBeingsMap() {
        return beingsMap;
    }


    public static Gamer getGamer() {
        return gamer;
    }

    public static void setGamer(Gamer gamer) {
        World.gamer = gamer;
    }


    public static RenderThread getRenderThread() {
        return renderThread;
    }


    public static boolean isPassable(Coord coord) {
        boolean result = true;

        GameObject temp;

        if ((temp = backgroundsMap.getT(coord)) != null)
            result = ((Block) temp).getPassableness() && result;

        if ((temp = blocksMap.getT(coord)) != null)
            result = ((Block) temp).getPassableness() && result;

        if (backgroundsMap.getT(coord) == null)
            result = false;


        return result;
    }
}
