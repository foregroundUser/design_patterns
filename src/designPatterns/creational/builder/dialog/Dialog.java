package designPatterns.creational.builder.dialog;

public class Dialog {
    private final double height;
    private final double width;
    private final String title;
    private final String body;

    private Dialog(Builder b) {
        this.height = b.height;
        this.width = b.width;
        this.title = b.title;
        this.body = b.body;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "height=" + height +
                ", width=" + width +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public static class Builder {
        private double height;
        private double width;
        private String title;
        private String body;

        public Builder setHeight(double h) {
            height = h;
            return this;
        }

        public Builder setWidth(double w) {
            width = w;
            return this;
        }

        public Builder setTitle(String t) {
            title = t;
            return this;
        }

        public Builder setBody(String b) {
            body = b;
            return this;
        }

        public Dialog build() {
            if (height == 0 & width == 0) {
                throw new IllegalStateException("Width va height majburiy ");

            }
            return new Dialog(this);

        }
    }
}
