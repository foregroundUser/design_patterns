package designPatterns.creational.prototype.doc;

public class DocumentFactory {
    private Document document;

    public DocumentFactory(Document document) {
        this.document = document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document cloneDocument() {
        return document.clone();
    }
}
