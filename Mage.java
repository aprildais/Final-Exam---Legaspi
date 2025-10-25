import java.util.Random;

class Mage extends Character {
    public Mage() {
        // Mage starts with high MP, lower HP
        super("Mage", 90, 100, 60);
    }

    // Skill 1: Fireball (cost 25 MP)
    // Magic attack that deals strong damage
    @Override
    public void skill1(Character enemy) {
        if (mp < 25) {
            System.out.println("Not enough MP!");
            return;
        }
        mp -= 25;
        int damage = new Random().nextInt(21) + 20; // 20–40 damage
        enemy.hp -= damage;
        if (enemy.hp < 0) enemy.hp = 0;
        System.out.println(name + " casts Fireball! Deals " + damage + " magic damage!");
    }

    // Skill 2: Ice Block (cost 60 MP)
    // Immune to all damage for 2 turns
    @Override
    public void skill2(Character enemy) {
        if (mp < 60) {
            System.out.println("Not enough MP!");
            return;
        }
        mp -= 60;
        defending = true;
        System.out.println(name + " uses Ice Block! Immune to all damage for 2 turns!");
    }
}
