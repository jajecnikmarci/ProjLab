package kevesse_kokanyolo_kod.views;

public class IntPair {
    public int x;
        public int y;

        public IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public IntPair scale(double scalar) {
            double l = x * x  + y * y;
            l = Math.sqrt(l);
            return new IntPair((int)(x / l * scalar), (int)(y / l * scalar));
        }
        public IntPair add(IntPair other) {
            return new IntPair(x + other.x, y + other.y);
        }
        public IntPair sub(IntPair other) {
            return new IntPair(x - other.x, y - other.y);
        }

        public double length() {
            return Math.sqrt(x * x + y * y);
        }
}
