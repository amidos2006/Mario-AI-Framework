This is an updated version for the Mario-AI framework. This new code will support better interface for planning track, level generation track, and possibly the learning track in the future. The framework comes with multiple different planning agents, level generators and thousands of levels that varies between generated levels and original mario level. Also, the framework is compatible with [Video Game Level Corpus (VGLC)](https://github.com/TheVGLC/TheVGLC) processed notations.

### How to use
------
#### Planning Track
Download the repo and run the [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file. It will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent on the [first Mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) from the original Super Mario Bros. The game will run for 20 time ticks and with Mario starting as small mario and visuals appearing. To change the agent just change the package name of the agent in the following code
```
printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("levels/original/lvl-1.txt"), 20, 0, true));
```
to any of the package names that are found in [`src/agents/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents) folder, feel free to use any in your work. If you want to play a level yourself uncomment the following code in [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file
```
//printResults(game.playGame(getLevel("levels/original/lvl-1.txt"), 200, 0));
```
and comment the agent running line from before. This code will run the framework to play the [first mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) of the original Super Mario Bros with 200 tick on the game clock and with Mario starting as small mario. Feel free to change the `0` to `1` to start as Large Mario or `2` to start as Fire Mario.

#### Level Generation Track
Download the repo and run the [`GenerateLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/GenerateLevel.java) from the [`src/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src) folder to test the framework. It will run `notch` generator to generate a level then it will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent to play that generated level. Feel free to try another generators by changing the package name of generator in the following line
```
MarioLevelGenerator generator = new levelGenerators.notch.LevelGenerator();
```
to any of the other package names of the other generator that can be found in in [`src/levelGenerators/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/levelGenerators) folder, feel free to use any in your work. If you want to play the level by yourself or change the AI playing agent check the Planning Track subsection.

### Features
- Better Interface for the framework
- Faster framework
- Using the original mario art
- Eleven different playing agents
- Agents now have a forward model, no more hacks for that
- Observation grids can be centered around mario or can reflect the current screen.
- Helper classes to check the observation grid instead of comparing integers
- Five different level generators
- Level generator have a forward model to test the levels
- Thousands of generated levels from winners of the level generation track
- Fifteen levels from the original mario bros
- Support event history for major game events
- A human readable level files

### Missing Features
- ~~The MarioAI framework core engine~~
- ~~Implementing a forward model and multiple different observations (based around mario/based around the screen center)~~
- ~~Implementing the original SMB graphics instead of Mario world graphics~~
- ~~Adding multiple agents from the previous competition~~
- ~~Isolating particle effects from game sprites~~
- ~~Only using the first SMB action set (no more shell carrying/wall jumping)~~
- ~~Documenting the interface~~
- ~~Adding Generated Levels~~
- ~~Adding the level generator interface~~
- ~~Adding the level generators to the framework~~
- ~~Better way to check the observation grid Using TileType and SpriteType~~
- ~~Adding event history for the game and agent to MarioResults~~
- ~~Modifying the original Mario levels to include more details~~
- Mix the TileType and TileFeature class
- Add more stats to MarioResult class similar to Gameplay Metrics
- Adding a simple MCTS agent and simple A* agent
- Koopa shells can come back to life after stomping on it
- Adding Monte Mario agent
- Multiple different backgrounds/palettes that the user can select from.
- Documenting the whole engine
- Mimicking the original SMB physics instead of SMW physics
- Adding the learning track interface

### Copyrights
This framework is not endorsed by Nintendo and only intended for research purposes. Mario is a Nintendo character which the authors doesn't own any rights to it. This framework by Ahmed Khalifa, based on the original Mario AI Framework by Sergey Karakovskiy, Noor Shaker and Julian Togelius, which in turn was based on Infinite Mario Bros by Markus Persson.