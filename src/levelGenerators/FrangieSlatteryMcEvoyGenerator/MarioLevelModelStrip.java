package levelGenerators.FrangieSlatteryMcEvoyGenerator;

import engine.core.MarioLevelModel;

// A class for handling strips of MarioLevelModel content
public class MarioLevelModelStrip {
    int sizeX;
    int sizeY;
    char [][]strip;

    public MarioLevelModelStrip(int x, int y, String string) {
        sizeX = x;
        sizeY = y;
        strip = new char[sizeX][sizeY];
        
        int index = 0;
        for (int nx = 0; nx < sizeX; nx++) {
            for (int ny = 0; ny < sizeY; ny++) {
                strip[nx][ny] = string.charAt(index++);
            }
        }
    }

    public void setLevelStrip(MarioLevelModel model, int position, int height) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                model.setBlock(position+x, height+y, strip[x][y]);
            }
        }
    }

    public void setLevelStrip(MarioLevelModel model, int position) {
        setLevelStrip(model, position, 0);
    }
}
