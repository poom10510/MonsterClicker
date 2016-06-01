# MonsterClicker
Software Specification and Design : Final Project

Project name: **MonsterClicker**

Git link : https://github.com/poom10510/MonsterClicker

**Team Member**

1) Kitipoom Kongpetch 				5710546160

2) Wanchanapon Thanwaranurak		5710546607

3) Phasin Sarunpornkul				5710546356

#Design  Principles

**GRASP**

1) Creator

- MainActivity creates layout on screen.
- Game creates data in game.
	
2) Information Experts

- We can know all data from Game class.
	
3) Low Coupling

- In Game, Player and ally can attack enemy by don’t have enemy in class. 

4) Control

- Game is control class. It have method to control every class.
	
5) High Cohesions

- LevelUp use to upgrade all Ally in game.
- Calculator use calculate all value in Ally,Player,and Enemy.

**SRP Single Responsibility Principle**

- Calculator class calculate all value in Ally,Player,and Enemy.

**OCP Open Closed Principle**

- Archer, Caster and Warrior are subclass of Item class. They have Action(Game game) that have difference effect.

**DRY**

- We have data of hero and enemy only one in program by send it to all class.

**LSP Liscov Substitution Principle**

- Archer, Caster and Warrior are subclass of Ally class. they have Action(Player player, Enemy enemy) that difference work in each class

**Polymorphism**

- Ally,Item,Calculator,Keyplay and Memento are abstract class.

#Design Pattern

1) Singleton pattern

Class : Game.java

- Use in Game class to create it only one time because it is mediator between 
           others model class and MainActivity.java. 

2) State pattern

Contain 2 states : StateCasterAttack.java and StateCasterHeal.java 

- Use in Caster class to change state between attack and heal to help caster know they should attack enemy or heal player.

3) Observer pattern

Class : MainActivity(Observer), Game(Observable)

- Use to update status (health bar) when have change in Game class when player attack enemy or enemy attack player.
 
4) Iterator pattern

Class : AnimationIterator.java use in MainActivity.java

- First, we want to create animation with thread then difficulties and very long code. we decide to use Iterator pattern, it comfortable just we set calculate in method next(),So, each character can make animation by yourself. 

5) Memento pattern

Class : Memento.java use in Game.java,Player.java,Archer.java,Warrior.java,Caster.java.

- We want to save and load file in internal memory, game will be continue when re-open game. We use memento for keeping only necessary data.
