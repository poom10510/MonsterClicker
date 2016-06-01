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

	MainActivity is create layout on screen.
	
2) Information Experts

	We can know all data from Game class.
	
3) Low Coupling

	Calculator ca

4) Control

	Game is control class. It have method to control every class.
	
5) High Cohesions

**DRY**

We have data of hero and enemy only one in program

#Design Pattern

1) Singleton pattern

Class that use this pattern : Game.java

Use in Game class to create it only one time because 

2) State pattern
 Use in Caster class to change state between attack and heal to help caster know they should attack enemy or heal player.
เพราะcasterจะได้รู้ตัวเอง ว่าเมื่อไรควรฮิว เมื่อไรควรตี จะได้ไม่ต้องให้คาสอื่นมายุงกับหน้าที่ของ caster

3) Observer pattern

Use to update status when have change in Game class when player attack enemy or enemy attack player  เพราะตอนถูกตีจะได้ลดเลือดทันที
 
4) Iterator pattern

First, we want to create animation with thread then difficulties and very long code. So, we decide to use Iterator pattern, it comfortable just we set calculate in method next(), we can create animation in game.

5) Memento pattern

	Use in Game,Player,Enemy,Archer,Warrior and Caster to save file and load file and keep as necessary data. 
