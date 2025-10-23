import java.util.Scanner;
import java.util.Random;

public class MainGameLegaspi{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=== FIGHTING ARENA ===");
        System.out.println("Choose your Character:");
        System.out.println("[1] Warrior\n[2] Mage");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        // Player and enemy setup
        Character player, enemy;
        if (choice == 1) {
            player = new Warrior();
            enemy = new Mage();
        } else {
            player = new Mage();
            enemy = new Warrior();
        }

        System.out.println("\nYou are a " + player.name + "! Prepare to fight a " + enemy.name + "!\n");

        while (player.isAlive()) {
            player.showStatus();
            enemy.showStatus();

            // Player chooses an action
            System.out.println("\nChoose an action:");
            System.out.println("[1] Attack");
            System.out.println("[2] Defend");
            System.out.println("[3] Skill 1");
            System.out.println("[4] Skill 2");
            System.out.println("[5] Use Item");
            System.out.print("Enter choice: ");
            int action = sc.nextInt();

            // Player’s turn
            switch (action) {
                case 1 -> player.attack(enemy);
                case 2 -> player.defend();
                case 3 -> player.skill1(enemy);
                case 4 -> player.skill2(enemy);
                case 5 -> player.useItem();
                default -> System.out.println("Invalid action!");
            }

            if (enemy.isAlive()) {
                int enemyMove = rand.nextInt(4) + 1;
                if (enemyMove == 1) enemy.attack(player);
                else if (enemyMove == 2) enemy.defend();
                else if (enemyMove == 3) enemy.skill1(player);
                else enemy.skill2(player);
            } 
            // If enemy defeated
            else {
                System.out.println("Enemy defeated!");

                // Random potion drop (healing or mana)
                if (rand.nextBoolean()) {
                    player.healPotions++;
                    System.out.println("You found a Healing Potion!");
                } else {
                    player.manaPotions++;
                    System.out.println("You found a Mana Potion!");
                }

                // Spawn a new enemy of opposite class
                if (enemy instanceof Warrior)
                    enemy = new Mage();
                else
                    enemy = new Warrior();

                System.out.println("A new " + enemy.name + " appears!");
                player.showStatus();
            }

            // Break loop if player dies
            if (player.hp <= 0) break;

            System.out.println("\n-----------------------------------\n");
        }

        // Game over message
        System.out.println("You were defeated... Game Over.");
    }
}