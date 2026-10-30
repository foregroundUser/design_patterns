package designPatterns.creational.prototype.game;

public class CharacterFactory {
    private GameCharacter character;

    public CharacterFactory(GameCharacter character) {
        this.character = character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter cloneCharacter() {
        return character.clone();
    }
}
