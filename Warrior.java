import java.util.Random;

class Warrior extends Character {
    public Warrior() {
        // Warrior starts with high HP and SP, low MP
        super("Warrior", 120, 40, 100);
    }

    // Skill 1: Bloodthirst (cost 30 SP)
    // Next attack heals the Warrior for 50% of damage dealt
    @Override
    public void skill1(Character enemy) {
        if (sp < 30) {
            System.out.println("Not enough SP!");
            return;
        }
        sp -= 30;
        int damage = new Random().nextInt(16) + 15; // 15–30 damage
        enemy.hp -= damage;
        int heal = damage / 2; // Heal for half the damage
        hp += heal;
        if (hp > maxHP) hp = maxHP;
        System.out.println(name + " uses Bloodthirst! Deals " + damage + " damage and heals " + heal + " HP!");
    }

    // Skill 2: Shield Block (cost 50 SP)
    // Reduces incoming damage for 2 turns
    @Override
    public void skill2(Character enemy) {
        if (sp < 50) {
            System.out.println("Not enough SP!");
            return;
        }
        sp -= 50;
        defending = true;
        System.out.println(name + " uses Shield Block! Reduces damage by 40% for 2 turns!");
    }
}