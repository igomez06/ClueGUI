package clue;

public class Card {
	private String name;
	private CardType type;

	public enum CardType { ROOM, WEAPON, PERSON}

	public Card(String name, CardType type ) {
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public CardType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	
}
