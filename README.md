<p align="center">
<a href="#features">Features</a> &mdash; <a href="#use">How To Use</a> &mdash; <a href="#papers">Related Papers</a> &mdash; <a href="#missing">Missing Features</a> &mdash; <a href="#copyrights">Copyrights</a>
</p>
<p align="center">
<img width="300" height="300" alt="Robin Baumgarten A* agent" src="https://raw.githubusercontent.com/amidos2006/Mario-AI-Framework/master/img/frameworkAD.gif">
</p>
<p align="center">
  <b>Current Framework Version: 0.8.0</b>
</p>

The Mario AI framework is a framework for using AI methods with a version of Super Mario Bros.

This is an updated version for the Mario AI Framework. As the first version was released in 2009, this is the tenth anniversary edition, integrating features from all previous versions and adding several new features. This new code includes a better interface for playing the game with planning algorithms (the planning track of the competition), generating levels (the level generation track), and possibly will support the learning track in the future . The framework comes with multiple different planning agents, level generators and thousands of levels including generated levels from diffeent generators as well as the original Mario levels. Also, the framework is compatible with [Video Game Level Corpus (VGLC)](https://github.com/TheVGLC/TheVGLC) processed notations.

If you want to access the old framework, feel free to check out the old websites for the previous competitions ([2015](https://sites.google.com/site/platformersai/platformer-ai-competition) - [2012](https://sites.google.com/site/noormario/home?pli=1) - [2011](https://sites.google.com/a/marioai.com/www/home) - [2009](http://julian.togelius.com/mariocompetition2009/)).

<h3 id="features">Features</h3>

------
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

<h3 id="use">How To Use</h3>

------
#### Planning Track
Download the repo and run the [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file. It will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent on the [first Mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) from the original Super Mario Bros. The game will run for 20 seconds (in-game time) and with Mario starting as small Mario and visuals appearing. To change the agent just change the package name of the agent in the following code
```
printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("levels/original/lvl-1.txt"), 20, 0, true));
```
to any of the package names that are found in [`src/agents/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents) folder, feel free to use any in your work. If you want to play a level yourself uncomment the following code in [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file
```
//printResults(game.playGame(getLevel("levels/original/lvl-1.txt"), 200, 0));
```
and comment the agent running line from before. This code will run the framework to play the [first mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) of the original Super Mario Bros with 200 tick on the game clock and with Mario starting as small mario. Feel free to change the `0` to `1` to start as Large Mario or `2` to start as Fire Mario.

#### Level Generation Track
Download the repo and run the [`GenerateLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/GenerateLevel.java) from the [`src/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src) folder to test the framework. It will run the `notch` generator to generate a level then it will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent to play that generated level. Feel free to try another generators by changing the package name of generator in the following line
```
MarioLevelGenerator generator = new levelGenerators.notch.LevelGenerator();
```
to any of the other package names of the other generator that can be found in in [`src/levelGenerators/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/levelGenerators) folder, feel free to use any in your work. The generators runs for maximum time of 5 hours to generate a level of 150x16 tiles using the following line:
```
String level = generator.getGeneratedLevel(new MarioLevelModel(150, 16), new MarioTimer(5*60*60*1000));
```
If you want to play the level by yourself or change the AI playing agent check the Planning Track subsection.

<h3 id="papers">Related Papers</h3>

------
The following paper describes the original Mario AI Benchmark:
- [[2012] The Mario AI Benchmark and Competitions](http://julian.togelius.com/Karakovskiy2012The.pdf) by Sergey Karakovskiy and Julian Togelius. Published in the IEEE Transactions on Computational Intelligence and AI in Games (TCIAG), volume 4 issue 1, 55-67.

The following list show all the papers that talk mainly about playing the game in the Mario AI framework:
- [[2009] Super Mario Evolution](http://julian.togelius.com/Togelius2009Super.pdf) by Julian Togelius, Sergey Karakovskiy, Jan Koutnik and Jurgen Schmidhuber. Published in the proceedings of the IEEE Symposium on Computational Intelligence and Games.
- [[2010] The 2009 Mario AI Competition](http://julian.togelius.com/Togelius2010The.pdf) by Julian Togelius, Sergey Karakovskiy and Robin Baumgarten. Published in the proceedings of the IEEE Congress on Evolutionary Computation (CEC).
- [[2013] The Mario AI Championship 2009–2012](http://julian.togelius.com/Togelius2013The.pdf) by Julian Togelius, Noor Shaker, Sergey Karakovskiy and Georgios N. Yannakakis. Published in AI Magazine, 34(3), 89-92.
- [[2013] Imitating human playing styles in Super Mario Bros](http://julian.togelius.com/Ortega2013Imitating.pdf) by Juan Ortega, Noor Shaker, Julian Togelius and Georgios N. Yannakakis. Published in the Entertainment Computing, Elsevier, vol. 4, pp. 93-104.
- [[2014] Monte Mario: Platforming with MCTS](http://julian.togelius.com/Jacobsen2014Monte.pdf) by Emil Juul Jacobsen, Rasmus Greve and Julian Togelius. Published in the proceedings of the ACM Conference on Genetic and Evolutionary Computation.

The following is a list of papers about level generation in Mario AI Framework:
- [[2010] The 2010 Mario AI Championship: Level Generation Track](https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=6003769) by Noor Shaker, Julian Togelius, Georgios N. Yannakakis, Ben Weber, Tomoyuki Shimizu, Tomonori Hashiyama, Nathan Sorenson, Philippe Pasquier, Peter Mawhorter, Glen Takahashi, Gillian Smith, and Robin Baumgarten. Published in the IEEE Transactions on Computational Intelligence and Games.
- [[2010] Towards Automatic Personalized Content Generation for Platform Games](https://www.aaai.org/ocs/index.php/AIIDE/AIIDE10/paper/viewFile/2135/2546) by Noor Shaker, Georgios Yannakakis and Julian Togelius. Published in the procedings of AIIDE Conference on AI and Interactive Digital Entertainment.
- [[2012] Evolving Personalized Content for Super Mario Bros Using Grammatical Evolution](http://julian.togelius.com/Shaker2012EvolvingPersonalized.pdf) by Noor Shaker, Georgios N. Yannakakis, Julian Togelius, Miguel Nicolau, and Michael O'Neill. Published in the AAAI Conference on Artificial Intelligence and Interactive Digital Entertainment (AIIDE).
- [[2012] Patterns and Procedural Content Generation](http://julian.togelius.com/Dahlskog2012Patterns.pdf) by Steve Dahlskog and Julian Togelius. Published in the Proceedings of the FDG Workshop on Design Patterns in Games (DPG).
- [[2013] Patterns as Objectives for Level Generation](http://julian.togelius.com/Dahlskog2013Patterns.pdf) by Steve Dahlskog and Julian Togelius. Published in the Proceedings of the Workshop on Design Patterns in Games at FDG.
- [[2014] Procedural Content Generation Using Patterns as Objectives](http://julian.togelius.com/Dahlskog2014Procedural.pdf) by Steve Dahlskog and Julian Togelius. Published in the Proceedings of EvoGames, part of EvoStar.
- [[2014] A Comparative Evaluation of Procedural Level Generators in the Mario AI Framework](http://julian.togelius.com/Horn2014Comparative.pdf) by Britton Horn, Steve Dahlskog, Noor Shaker, Gillian Smith and Julian Togelius. Published in the Proceedings of Foundations of Digital Games.
- [[2014] Experiments in Map Generation using Markov Chains](http://www.fdg2014.org/papers/fdg2014_paper_29.pdf) by Sam Snodgrass and Santiago Ontañón. Published in the procceding of Foundation of Digital Games.
- [[2014] A Hierarchical Approach to Generating Maps Using Markov Chains](https://www.aaai.org/ocs/index.php/AIIDE/AIIDE14/paper/viewPaper/8984) by Sam Snodgrass and Santiago Ontanon. Published in the Proceedings of the Tenth Annual AAAI Conference on Artificial Intelligence and Interactive Digital Entertainment.
- [[2015] A Hierarchical MdMC Approach to 2D Video Game Map Generation](https://www.aaai.org/ocs/index.php/AIIDE/AIIDE15/paper/download/11518/11380) by Sam Snodgrass and Santiago Ontañón. Published in the Eleventh Artificial Intelligence and Interactive Digital Entertainment Conference.
- [[2018] Generating Levels That Teach Mechanics](https://arxiv.org/pdf/1807.06734.pdf) by Michael Cerny Green, Ahmed Khalifa, Gabriella A. B. Barros, Andy Nealen and Julian Togelius. Published in the proceeding of Foundation of Digital Games.
- [[2018] Evolving Mario Levels in the Latent Space of a Deep Convolutional Generative Adversarial Network](https://arxiv.org/pdf/1805.00728.pdf) by Vanessa Volz, Jacob Schrum, Jialin Liu, Simon M. Lucas, Adam Smith and Sebastian Risi. Published in the proceeding of the ACM Conference on Genetic and Evolutionary Computation.
- [[2019] Intentional Computational Level Design](https://arxiv.org/pdf/1904.08972.pdf) by Ahmed Khalifa, Michael Cerny Green, Gabriella A. B. Barros and Julian Togelius. Published in the proceeding of the ACM Conference on Genetic and Evolutionary Computation.

The following list includes papers that do not fit in the previous categories but still use the Mario AI Framework:
- [[2009] Modeling Player Experience in Super Mario Bros](http://julian.togelius.com/Pedersen2009Modeling.pdf) by Chris Pedersen, Julian Togelius and Georgios N. Yannakakis. Published in the Proceedings of the IEEE Symposium on Computational Intelligence and Games.
- [[2013] Decision Making Styles as Deviation from Rational Action A Super Mario Case Study](http://julian.togelius.com/Holmgard2013Decision.pdf) by Christoffer Holmgård, Julian Togelius and Georgios N. Yannakakis. Published in the Proceedings of the Artificial Intelligence in Digital Interactive Entertainment (AIIDE) conference.
- [[2013] The Turing Test Track of the 2012 Mario AI Championship: Entries and Evaluation](http://julian.togelius.com/Shaker2013The.pdf) by Noor Shaker, Julian Togelius, Georgios N. Yannakakis, Likith P. K. Satish, Vinay S. Ethiraj, Stefan J. Johansson, Robert Reynolds, Leonard Kinnaird-Heether, Tom Schumann and Marcus Gallagher. Published in the Proceedings of the IEEE Conference on Computational Intelligence and Games.

We are aware that this list is not complete. If you want your paper added, please [contact us](mailto:ahmed@akhalifa.com) and we will add it to the list.

<h3 id="missing">Missing Features</h3>

------
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
- ~~Adding Punishing Model to the Engine where the player dies when certain basic event fires~~
- Allow the punishing model to tackle more complex events that need to be infered
- Allow Agent debugging by drawing the searched trajectories like the Robin A* video
- Adapt more agents to the new Mario-Framework
- Adapt more level generator to the new Mario-Framework
- Add more stats to MarioResult class similar to Gameplay Metrics
- Mix the TileType and TileFeature class
- Adding a simple MCTS agent and simple A* agent
- Koopa shells can come back to life after stomping on it
- Adding Monte Mario agent
- Multiple different backgrounds/palettes that the user can select from.
- Documenting the whole engine
- Mimicking the original SMB physics instead of SMW physics
- Adding the learning track interface

<h3 id="copyrights">Copyrights</h3>

------
This framework is not endorsed by Nintendo and is only intended for research purposes. Mario is a Nintendo character which the authors don't own any rights to. Nintendo is also the sole owner of all the graphical assets in the game. Any use of this framework is expected to be on a non-commercial basis. This framework was created by [Ahmed Khalifa](https://scholar.google.com/citations?user=DRcyg5kAAAAJ&hl=en), based on the original Mario AI Framework by [Sergey Karakovskiy](https://scholar.google.se/citations?user=6cEAqn8AAAAJ&hl=en), [Noor Shaker](https://scholar.google.com/citations?user=OK9tw1AAAAAJ&hl=en), and [Julian Togelius](https://scholar.google.com/citations?user=lr4I9BwAAAAJ&hl=en), which in turn was based on [Infinite Mario Bros](https://fantendo.fandom.com/wiki/Infinite_Mario_Bros.) by Markus Persson.
