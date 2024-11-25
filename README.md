# N Body

## A physics simulation

- This project is a simulation of the famous N body problem from physics and astronomy. This project is capable of simulating the motion of an arbitary number of planets with different masses and sizes.
- This project can be for anyone. It can be used as an astronomical simulation, or it can be used just for fun through playing around with many planets.
- This project is of interest to me because I am interested in physics and astronomy, and this is a perfect combonation of those two.

## User stories phase 1
- I wish to be able to add and remove an arbitrary number of planets to NBodySimulation
- I wish for these planets I add to be able to be added with different masses, positions, and velocities 
- I wish to be able to view the planets I have added to NBodySimulation
- I wish to be able to change and set G, the gravitational constant
- I wish to be able to run ticks that update the position of planets
- I wish to make the planets obey the laws of physics
- I wish to be able to not crash the program when inputting unexpected inputs (such as a string instead of an int or indexing outside an array)
- I wish for the program to tell me what what error I made when inputting an unexpected input
- I wish to be able to view ticks as they are running to see the changing positions of the planets
- I wish to be able to type "h" to view a list of commands that I can use

## User stories phase 2
- I wish to be able to save the current planets and gravitational constant in NBodySimulation to a file
- I wish to be able to load a list of planets and gravitational constant from a file

- Phase 4: Task 2 example log of program:
- A planet was added
- A planet was added
- A planet was added
- A planet was added
- Planets ticked
- Planets ticked
- A planet was added
- Planets ticked
- Gravitational constant changed
- Planets ticked
- Planets ticked
- A planet was added
- Planets ticked
- Program saved

Phase 4: Task 3

There are a few changes I could make to my program to refactor it. For instance, JsonWriter and JsonReader are both used by ImageViewer and they have similar sorts of methods and constants, so I could combine these into one class. This new class would be called something like "JsonUser", and would handle both the writing and reading of the Json file. This way, the varibles source (in JsonReader) and destination (in JsonWriter) could be combined into a new varible called "fileDirectory". In addition, ImageViewer would only have to instantiate one JsonUser instead of one JsonReader and JsonWriter. In addition, this refactoring would make ImageViewer more straightforwards because it only has to deal with one object whoes goal is data persistance. These are some reasons why I think this refactoring could be benificial for the program.