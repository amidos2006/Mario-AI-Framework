package engine.helper;

import java.util.ArrayList;

public enum TileFeatures {
    BLOCK_UPPER,
    BLOCK_ALL,
    BLOCK_LOWER,
    SPECIAL,
    LIFE,
    BUMPABLE,
    BREAKABLE,
    PICKABLE,
    ANIMATED,
    SPAWNER;
    
    public static ArrayList<TileFeatures> getTileType(int index) {
	ArrayList<TileFeatures> features = new ArrayList<>();
	switch(index) {
	case 1:
	case 2:
	case 14:
	case 18:
	case 19:
	case 20:
	case 21:
	case 4:
	case 5:
	    features.add(TileFeatures.BLOCK_ALL);
	    break;
	case 43:
	case 44:
	case 45:
	case 46:
	    features.add(TileFeatures.BLOCK_LOWER);
	    break;
	case 48:
	    features.add(TileFeatures.BLOCK_UPPER);
	    features.add(TileFeatures.LIFE);
	    features.add(TileFeatures.BUMPABLE);
	    break;
	case 49:
	    features.add(TileFeatures.BUMPABLE);
	    features.add(TileFeatures.BLOCK_UPPER);
	    break;
	case 3:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.SPAWNER);
	    break;
	case 8:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.SPECIAL);
	    features.add(TileFeatures.BUMPABLE);
	    features.add(TileFeatures.ANIMATED);
	    break;
	case 11:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.BUMPABLE);
	    features.add(TileFeatures.ANIMATED);
	    break;
	case 6:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.BREAKABLE);
	    break;
	case 7:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.BUMPABLE);
	    break;
	case 15:
	    features.add(TileFeatures.PICKABLE);
	    features.add(TileFeatures.ANIMATED);
	    break;
	case 50:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.SPECIAL);
	    features.add(TileFeatures.BUMPABLE);
	    break;
	case 51:
	    features.add(TileFeatures.BLOCK_ALL);
	    features.add(TileFeatures.LIFE);
	    features.add(TileFeatures.BUMPABLE);
	    break;
	}
	return features;
    }
}
