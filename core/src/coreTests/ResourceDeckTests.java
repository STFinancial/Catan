package coreTests;

//import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import stf.gamePieces.ResourceCard;
import stf.gamePieces.ResourceCollection;
import stf.gamePieces.ResourceType;

public class ResourceDeckTests {

	ResourceCollection deck;
	
	@Test
	public void testConstructor() {
		
		deck = new ResourceCollection(ResourceType.BRICK);
		Assert.assertNotNull(deck);
	}
	
	@Test
	public void testCorrectType() {
		deck = new ResourceCollection(ResourceType.BRICK);
		Assert.assertTrue(deck.getType() == ResourceType.BRICK);
	}
	
	@Test
	public void testCardType() {
		ResourceCard card;
		deck = new ResourceCollection(ResourceType.BRICK);
		card = deck.dealCard();
		Assert.assertTrue(card.getType() == ResourceType.BRICK);
		deck.returnCard(card);
	}
	
	@Test
	public void testCorrectNumCards() {
		deck = new ResourceCollection(ResourceType.BRICK);
		for (int i = 0; i < 19; ++i) {
			deck.dealCard();
		}
		Assert.assertNull(deck.dealCard());
	}

}
