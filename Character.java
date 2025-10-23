import java.util.Scanner;
import java.util.Random;

abstract class Character {
    String name;       // Character name
    int hp, mp, sp;    // Health, Mana, Stamina
    int maxHP, maxMP, maxSP; // Max values for stats
    int healPotions = 0;     // Healing potion counter
    int manaPotions = 0;     // Mana potion counter
    boolean defending = false; // If true, reduces next damage

    
    public Character(String name, int hp, int mp, int sp) {
        this.name = name;
        this.hp = this.maxHP = hp;
        this.mp = this.maxMP = mp;
        this.sp = this.maxSP = sp;
    }

    // Checks if character is still alive (HP > 0)
    public boolean isAlive() {
        return hp > 0;
    }

    // Displays character’s current stats
    public void showStatus() {
        System.out.println("\n" + name + " | HP: " + hp + "/" + maxHP + 
                           " | MP: " + mp + "/" + maxMP + 
                           " | SP: " + sp + "/" + maxSP + 
                           " | Healing Potions: " + healPotions + 
                           " | Mana Potions: " + manaPotions);
    }

    // Basic Attack (costs 2 SP)
    public void attack(Character enemy) {
        if (sp < 2) {
            System.out.println(name + " has not enough SP!");
            return;
        }
        sp -= 2;
        int damage = new Random().nextInt(11) + 10; // Random damage between 10–20

        // If enemy is defending, reduce damage by 40%
        if (enemy.defending) {
            damage *= 0.6;
            enemy.defending = false; // reset defend state after attack
        }

        enemy.hp -= damage;
        if (enemy.hp < 0) enemy.hp = 0;

        System.out.println(name + " attacks and deals " + damage + " damage!");
    }

    // Defend (costs 3 SP)
    public void defend() {
        if (sp < 3) {
            System.out.println(name + " has not enough SP!");
            return;
        }
        sp -= 3;
        defending = true;
        System.out.println(name + " defends and will take less damage next turn!");
    }

    // Use Item (Healing or Mana Potion)
    public void useItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose item to use:");
        System.out.println("[1] Healing Potion (" + healPotions + ")");
        System.out.println("[2] Mana Potion (" + manaPotions + ")");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        // Healing Potion - restores HP
        if (choice == 1 && healPotions > 0) {
            healPotions--;
            int heal = 30;
            hp += heal;
            if (hp > maxHP) hp = maxHP;
            System.out.println(name + " used a Healing Potion and restored " + heal + " HP!");
        }
        // Mana Potion - restores MP
        else if (choice == 2 && manaPotions > 0) {
            manaPotions--;
            int restore = 30;
            mp += restore;
            if (mp > maxMP) mp = maxMP;
            System.out.println(name + " used a Mana Potion and restored " + restore + " MP!");
        }
        // No potion available
        else {
            System.out.println("No potions available!");
        }
    }

    // Abstract methods (unique skills for each subclass)
    public abstract void skill1(Character enemy);
    public abstract void skill2(Character enemy);
}