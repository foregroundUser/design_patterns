package designPatterns.creational.prototype.game;

public class GameCharacter implements Copyable {
    private int id;
    private String name;
    private CharacterLevel level;
    private String weapon;

    public GameCharacter(int id, String name, CharacterLevel level, String weapon) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.weapon = weapon;
    }

    @Override
    public GameCharacter clone() {
        return new GameCharacter(id, name, level, weapon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterLevel getLevel() {
        return level;
    }

    public void setLevel(CharacterLevel level) {
        this.level = level;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
