package coreTests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import stf.gamePieces.*;

public class TableTests {

	Table table;
	
	@Test
	public void testTableConstructorNoPlayers() {
		table = new Table(0, 0);
		Assert.assertNotNull(table);
	}
	
	@Test
	public void testTableConstructorAllPlayers() {
		table = new Table(4, 0);
		Assert.assertNotNull(table);
	}
	
	@Test
	public void testTableConstructorAllAI() {
		table = new Table(0, 4);
		Assert.assertNotNull(table);
	}
	
	@Test
	public void testTableConstructorMultEach() {
		table = new Table(2, 2);
		Assert.assertNotNull(table);
	}
	
	@Test
	public void testEndTurnMove() {
		table = new Table(2,2);
		
		Person person1 = table.getCurrentPlayer();
		Move move = new Move(MoveType.ENDTURN, person1);
		table.performMove(move);
		Person person2 = table.getCurrentPlayer();
		Assert.assertFalse(person2.getColor() == person1.getColor());
		
	}
	
	@Test
	public void testEndTurnMoveCorrectMoves() {
		table = new Table(2,2);
		
		Person person1 = table.getCurrentPlayer();
		Move move = new Move(MoveType.ENDTURN, person1);
		table.performMove(move);
		
		Person person2 = table.getCurrentPlayer();
		move = new Move(MoveType.ENDTURN, person2);
		table.performMove(move);
		
		Person person3 = table.getCurrentPlayer();
		move = new Move(MoveType.ENDTURN, person3);
		table.performMove(move);
		
		Person person4 = table.getCurrentPlayer();
		move = new Move(MoveType.ENDTURN, person4);
		table.performMove(move);
		
		Person person5 = table.getCurrentPlayer();
		
		PlayerColor p1c = person1.getColor();
		PlayerColor p2c = person2.getColor();
		PlayerColor p3c = person3.getColor();
		PlayerColor p4c = person4.getColor();
		
		Assert.assertFalse(p1c == p2c ||
						   p1c == p3c ||
						   p1c == p4c ||
						   p2c == p3c ||
						   p2c == p4c ||
						   p3c == p4c ||
						   p1c != person5.getColor());
	}
}
