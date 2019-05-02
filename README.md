# Mario-AI Framework: 10th Anniversary Edition
This is an updated version for the Mario-AI framework. This new code will support better interface for planning/level generation/learning (future). We will also provide multiple different planning agents, level generators, original levels, and generated levels from previous years. Also, the framework is compatible with VGLC processed notations: https://github.com/TheVGLC/TheVGLC.

### How to use
Download the repo and run either `PlayLevel.java` or `GenerateLevel.java` from the `src/` folder to test the framework.
All the playing agents are in `src/agents/` folder, feel free to use any in your work. Check `PlayLevel.java` to see how to run an automated playthrough.
All the level generators are in `src/levelGenerators/` folder, feel free to use any in your work. Check `GenerateLevel.java` to see how to run a level generator.

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
- Add more stats to MarioResult class similar to Gameplay Metrics
- Mix the TileType and TileFeature class
- Adding a simple MCTS agent and simple A* agent
- Koopa shells can come back to life after stomping on it
- Adding Monte Mario agent
- Multiple different backgrounds/palettes that the user can select from.
- Documenting the whole engine
- Mimicking the original SMB physics instead of SMW physics
- Adding the learning track interface

### Copyrights
Mario is a Nintendo character which the authors doesn't own any rights to it. The framework is based on the core source code of Mario-AI framework which was a modification to Infinite Mario Bros by Notch.