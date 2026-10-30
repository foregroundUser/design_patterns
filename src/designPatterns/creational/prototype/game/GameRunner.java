package designPatterns.creational.prototype.game;

public class GameRunner {
    public static void main(String[] args) {
        GameCharacter original = new GameCharacter(1, "Warrior", CharacterLevel.NOOB, "Sword");

        CharacterFactory factory = new CharacterFactory(original);

        GameCharacter clone1 = factory.cloneCharacter();
        GameCharacter clone2 = factory.cloneCharacter();

        clone1.setId(2);
        clone1.setName("Archer");
        clone1.setLevel(CharacterLevel.MEDIUM);
        clone1.setWeapon("Bow");


        clone2.setId(3);
        clone2.setName("Mage");
        clone2.setLevel(CharacterLevel.EXPERT);
        clone2.setWeapon("Staff");

        System.out.println("Original: " + original);
        System.out.println("Clone 1:  " + clone1);
        System.out.println("Clone 2:  " + clone2);

        System.out.println();
        System.out.println("original == clone1: " + (original == clone1));
        System.out.println("original == clone2: " + (original == clone2));
        System.out.println("clone1 == clone2:   " + (clone1 == clone2));
    }
}
