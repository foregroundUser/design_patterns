package designPatterns.creational.prototype.doc;

import java.util.ArrayList;
import java.util.List;

public class DocumentRunner {
    public static void main(String[] args) {
        List<String> tags = new ArrayList<>(List.of("java", "pattern"));
        Document original = new Document(1, "Hisobot", "Asl matn", "Ali", tags);

        DocumentFactory factory = new DocumentFactory(original);

        Document clone1 = factory.cloneDocument();
        Document clone2 = factory.cloneDocument();

        clone1.setId(2);
        clone1.setTitle("Hisobot nusxa 1");
        clone1.setContent("Birinchi nusxa matni");
        clone1.getTags().add("draft");

        clone2.setId(3);
        clone2.setTitle("Hisobot nusxa 2");
        clone2.setContent("Ikkinchi nusxa matni");
        clone2.getTags().remove("java");
        clone2.getTags().add("final");

        System.out.println("Original: " + original);
        System.out.println("Clone 1:  " + clone1);
        System.out.println("Clone 2:  " + clone2);

        System.out.println();
        System.out.println("original == clone1:                 " + (original == clone1));
        System.out.println("original.tags == clone1.tags:       " + (original.getTags() == clone1.getTags()));
        System.out.println("clone1.tags == clone2.tags:         " + (clone1.getTags() == clone2.getTags()));
        System.out.println("original tags o'zgarmadimi:         " + original.getTags().equals(List.of("java", "pattern")));
    }
}
