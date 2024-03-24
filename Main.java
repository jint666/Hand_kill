import java.util.Scanner;
import java.security.SecureRandom;

class Player {
    int point1;
    int point2;
    double hp;
    double hpMax;

    void collide(Player opponent, int attackerNo, int defenderNo) {
        int newPoint1 = this.point1;
        int newPoint2 = this.point2;
        if (attackerNo == 1 && defenderNo == 1) {
            newPoint1 = (this.point1 + opponent.point1) % 10;
        } else if (attackerNo == 1 && defenderNo == 2) {
            newPoint1 = (this.point1 + opponent.point2) % 10;
        } else if (attackerNo == 2 && defenderNo == 1) {
            newPoint2 = (this.point2 + opponent.point1) % 10;
        } else if (attackerNo == 2 && defenderNo == 2) {
            newPoint2 = (this.point2 + opponent.point2) % 10;
        }
        this.point1 = newPoint1;
        this.point2 = newPoint2;
    }

    public Player(int point1, int point2, double hp) {
        this.point1 = point1;
        this.point2 = point2;
        this.hp = hp;
        this.hpMax = hp;
    }

    public String toString(int x) {
        return "Player" + x + "{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", hp=" + hp +
                '}';
    }
}

class Game {
    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    void processCollide(Player attacker, Player defender, int attackerNo) {
        double amount = 0;
        double upAmount = 0;
        if (attacker.point1 == 5 && attacker.point2 == 1) {
            amount = 1;
        } else if (attacker.point1 == 1 && attacker.point2 == 5) {
            amount = 1;
        } else if (attacker.point1 == 5 && attacker.point2 == 2) {
            amount = 2;
        } else if (attacker.point1 == 2 && attacker.point2 == 5) {
            amount = 2;
        } else if (attacker.point1 == 5 && attacker.point2 == 5) {
            amount = 5;
        } else if (attacker.point1 == 6 && attacker.point2 == 1) {
            amount = 0.5;
        } else if (attacker.point1 == 6 && attacker.point2 == 2) {
            amount = 1;
        } else if (attacker.point1 == 6 && attacker.point2 == 3) {
            amount = 1.5;
        } else if (attacker.point1 == 6 && attacker.point2 == 4) {
            amount = 2;
        } else if (attacker.point1 == 6 && attacker.point2 == 8) {
            amount = 4;
        } else if (attacker.point1 == 1 && attacker.point2 == 6) {
            amount = 0.5;
        } else if (attacker.point1 == 2 && attacker.point2 == 6) {
            amount = 1;
        } else if (attacker.point1 == 3 && attacker.point2 == 6) {
            amount = 1.5;
        } else if (attacker.point1 == 4 && attacker.point2 == 6) {
            amount = 2;
        } else if (attacker.point1 == 8 && attacker.point2 == 6) {
            amount = 4;
        } else if (attacker.point1 == 6 && attacker.point2 == 6) {
            amount = 5;
        } else if (attacker.point1 == 2 && attacker.point2 == 2) {
            amount = 1;
        } else if (attacker.point1 == 4 && attacker.point2 == 4) {
            amount = 4;
        } else if (attacker.point1 == 0 && attacker.point2 == 0) {
            amount = 5;
        } else if (attacker.point1 == 3 && attacker.point2 == 3) {
            upAmount = 2;
        } else if (attacker.point1 == 8 && attacker.point2 == 3) {
            upAmount = 2;
        } else if (attacker.point1 == 3 && attacker.point2 == 8) {
            upAmount = 2;
        } else if (attacker.point1 == 8 && attacker.point2 == 8) {
            upAmount = 2;
        } else if (attacker.point1 == 9 && attacker.point2 == 9) {
            amount = 2;
            upAmount = 2;
        } else if (attacker.point1 == 9 && attackerNo == 1) {
            amount = 1;
            upAmount = 1;
        } else if (attacker.point2 == 9 && attackerNo == 2) {
            amount = 1;
            upAmount = 1;
        }
        if (attacker.point1 == 7 && attackerNo == 1) {
            if (defender.point1 == 6 || defender.point2 == 6) {
                if (defender.point1 == 6) {
                    defender.point1 = 1;
                } else {
                    defender.point2 = 1;
                }
                upAmount = 1;
            } else {
                amount = 1;
            }
        } else if (attacker.point2 == 7 && attackerNo == 2) {
            if (defender.point1 == 6 || defender.point2 == 6) {
                if (defender.point1 == 6) {
                    defender.point1 = 1;
                } else {
                    defender.point2 = 1;
                }
                upAmount = 1;
            } else {
                amount = 1;
            }
        }
        if (attacker.point1 == 7 && attacker.point2 == 7) {
            amount = 2;
        }

        if (defender.point1 == 0 || defender.point2 == 0) {
            if (amount >= 0.5 && defender.point1 == 0) {
                amount -= 0.5;
                if (upAmount >= 0.5) {
                    upAmount -= 0.5;
                }
                defender.point1 = 1;
            }
            if (amount >= 0.5 && defender.point2 == 0) {
                amount -= 0.5;
                if (upAmount >= 0.5) {
                    upAmount -= 0.5;
                }
                defender.point2 = 1;
            }
        }
        reduceHp(defender, amount);
        increaseHp(attacker, upAmount);
    }

    void reduceHp(Player player, double amount) {
        player.hp -= amount;
        player.hp = Math.max(player.hp, 0);
    }

    void increaseHp(Player player, double amount) {
        player.hp += amount;
        player.hp = Math.min(player.hp, player.hpMax);
    }

    boolean checkGameOver() {
        return player1.hp == 0 || player2.hp == 0;
    }
}

public class Main {
    public static double Attack(int attackerPoint1, int attackerPoint2, int defenderPoint1, int defenderPoint2, int attackerNo) {
        double a = 0;
        if (attackerPoint1 == 5 && attackerPoint2 == 1) {
            a = 1;
        } else if (attackerPoint1 == 1 && attackerPoint2 == 5) {
            a = 1;
        } else if (attackerPoint1 == 5 && attackerPoint2 == 2) {
            a = 2;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 5) {
            a = 2;
        } else if (attackerPoint1 == 5 && attackerPoint2 == 5) {
            a = 5;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 1) {
            a = 0.5;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 2) {
            a = 1;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 3) {
            a = 1.5;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 4) {
            a = 2;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 8) {
            a = 4;
        } else if (attackerPoint1 == 1 && attackerPoint2 == 6) {
            a = 0.5;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 6) {
            a = 1;
        } else if (attackerPoint1 == 3 && attackerPoint2 == 6) {
            a = 1.5;
        } else if (attackerPoint1 == 4 && attackerPoint2 == 6) {
            a = 2;
        } else if (attackerPoint1 == 8 && attackerPoint2 == 6) {
            a = 4;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 6) {
            a = 5;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 2) {
            a = 1;
        } else if (attackerPoint1 == 4 && attackerPoint2 == 4) {
            a = 4;
        } else if (attackerPoint1 == 0 && attackerPoint2 == 0) {
            a = 5;
        } else if (attackerPoint1 == 9 && attackerPoint2 == 9) {
            a = 2;
        } else if (attackerPoint1 == 9 && attackerNo == 1) {
            a = 1;
        } else if (attackerPoint2 == 9 && attackerNo == 2) {
            a = 1;
        } else if (attackerPoint1 == 7 && attackerPoint2 == 7) {
            a = 2;
        } else if (attackerPoint1 == 7 && attackerNo == 1) {
            a = 1;
        } else if (attackerPoint2 == 7 && attackerNo == 2) {
            a = 1;
        }
        if (defenderPoint1 == 0 && a >= 0.5) {
            a -= 0.5;
        }
        if (defenderPoint2 == 0 && a >= 0.5) {
            a -= 0.5;
        }
        return a;
    }

    public static double Restore(int attackerPoint1, int attackerPoint2, int defenderPoint1, int defenderPoint2, int attackerNo) {
        double a = 0;
        if (attackerPoint1 == 3 && attackerPoint2 == 3) {
            a = 2;
        } else if (attackerPoint1 == 3 && attackerPoint2 == 8) {
            a = 2;
        } else if (attackerPoint1 == 8 && attackerPoint2 == 3) {
            a = 2;
        } else if (attackerPoint1 == 8 && attackerPoint2 == 8) {
            a = 2;
        } else if (attackerPoint1 == 9 && attackerPoint2 == 9) {
            a = 2;
        } else if (attackerPoint1 == 9 && attackerNo == 1) {
            a = 1;
        } else if (attackerPoint2 == 9 && attackerNo == 2) {
            a = 1;
        } else if (attackerPoint1 == 7 && attackerNo == 1 && (defenderPoint1 == 6 || defenderPoint2 == 6)) {
            a = 1;
        } else if (attackerPoint2 == 7 && attackerNo == 2 && (defenderPoint1 == 6 || defenderPoint2 == 6)) {
            a = 1;
        }
        return a;
    }

    public static double attackerProcess(int attackerPoint1, int attackerPoint2, int defenderPoint1, int defenderPoint2, int attackerNo, int defenderNo, double dihp, double hp) {
        double amount = 0;
        if (attackerPoint1 == 5 && attackerPoint2 == 1) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 1 && attackerPoint2 == 5) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 5 && attackerPoint2 == 2) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 2 && attackerPoint2 == 5) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 5 && attackerPoint2 == 5) {
            amount = 5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 1) {
            amount = 0.5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 2) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 3) {
            amount = 1.5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 4) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 8) {
            amount = 4;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 1 && attackerPoint2 == 6) {
            amount = 0.5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 2 && attackerPoint2 == 6) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 3 && attackerPoint2 == 6) {
            amount = 1.5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 4 && attackerPoint2 == 6) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 8 && attackerPoint2 == 6) {
            amount = 4;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 6 && attackerPoint2 == 6) {
            amount = 5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 2 && attackerPoint2 == 2) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 4 && attackerPoint2 == 4) {
            amount = 4;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 0 && attackerPoint2 == 0) {
            amount = 5;
            if (amount >= dihp) {
                amount *= 100;
            }
        } else if (attackerPoint1 == 3 && attackerPoint2 == 3) {
            if (hp < 3) {
                amount = 2.4;
            } else if (hp < 6) {
                amount = 1.6;
            } else if (hp < 9) {
                amount = 0.8;
            }
        } else if (attackerPoint1 == 8 && attackerPoint2 == 3) {
            if (hp < 3) {
                amount = 2.4;
            } else if (hp < 6) {
                amount = 1.6;
            } else if (hp < 9) {
                amount = 0.8;
            }
        } else if (attackerPoint1 == 3 && attackerPoint2 == 8) {
            if (hp < 3) {
                amount = 2.4;
            } else if (hp < 6) {
                amount = 1.6;
            } else if (hp < 9) {
                amount = 0.8;
            }
        } else if (attackerPoint1 == 8 && attackerPoint2 == 8) {
            if (hp < 3) {
                amount = 2.4;
            } else if (hp < 6) {
                amount = 1.6;
            } else if (hp < 9) {
                amount = 0.8;
            }
        } else if (attackerPoint1 == 9 && attackerPoint2 == 9) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (hp < 2) {
                amount += 3.5;
            } else if (hp < 4) {
                amount += 2.8;
            } else if (hp < 6) {
                amount += 2.1;
            } else if (hp < 8) {
                amount += 1.4;
            } else if (hp < 10) {
                amount += 0.7;
            }
        } else if (attackerPoint1 == 9 && attackerNo == 1) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (hp < 2) {
                amount += 1.75;
            } else if (hp < 4) {
                amount += 1.4;
            } else if (hp < 6) {
                amount += 1.05;
            } else if (hp < 8) {
                amount += 0.7;
            } else if (hp < 10) {
                amount += 0.35;
            }
        } else if (attackerPoint2 == 9 && attackerNo == 2) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (hp < 2) {
                amount += 1.75;
            } else if (hp < 4) {
                amount += 1.4;
            } else if (hp < 6) {
                amount += 1.05;
            } else if (hp < 8) {
                amount += 0.7;
            } else if (hp < 10) {
                amount += 0.35;
            }
        } else if (attackerPoint1 == 7 && attackerPoint2 == 7) {
            amount = 2;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (defenderPoint1 == 6 || defenderPoint2 == 6) {
                if (hp < 3) {
                    amount += 1.6;
                } else if (hp < 6) {
                    amount += 1.2;
                } else if (hp < 9) {
                    amount += 0.8;
                } else {
                    amount += 0.4;
                }
            }
        } else if (attackerPoint1 == 7 && attackerNo == 1) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (defenderPoint1 == 6 || defenderPoint2 == 6) {
                if (hp < 3) {
                    amount += 1.6;
                } else if (hp < 6) {
                    amount += 1.2;
                } else if (hp < 9) {
                    amount += 0.8;
                } else {
                    amount += 0.4;
                }
            }
        } else if (attackerPoint2 == 7 && attackerNo == 2) {
            amount = 1;
            if (amount >= dihp) {
                amount *= 100;
            }
            if (defenderPoint1 == 6 || defenderPoint2 == 6) {
                if (hp < 3) {
                    amount += 1.6;
                } else if (hp < 6) {
                    amount += 1.2;
                } else if (hp < 9) {
                    amount += 0.8;
                } else {
                    amount += 0.4;
                }
            }
        }
        if (attackerPoint1 == 6 && attackerNo == 1) {
            amount += 0.3;
        } else if (attackerPoint2 == 6 && attackerNo == 2) {
            amount += 0.3;
        } else if (attackerPoint1 == 5 && attackerNo == 1) {
            amount += 0.15;
        } else if (attackerPoint2 == 5 && attackerNo == 2) {
            amount += 0.15;
        } else if (attackerPoint1 == 0 && attackerNo == 1) {
            if (hp < 3) {
                amount += 0.8;
            } else if (hp < 6) {
                amount += 0.6;
            } else if (hp < 9) {
                amount += 0.4;
            } else {
                amount += 0.2;
            }
        } else if (attackerPoint2 == 0 && attackerNo == 2) {
            if (hp < 3) {
                amount += 0.8;
            } else if (hp < 6) {
                amount += 0.6;
            } else if (hp < 9) {
                amount += 0.4;
            } else {
                amount += 0.2;
            }
        }
        if (defenderNo == 1) {
            if (defenderPoint1 == 0) {
                amount -= 100;
            }
        } else {
            if (defenderPoint2 == 0) {
                amount -= 100;
            }
        }
        return amount;
    }

    public static boolean isAttack(int attackerPoint1, int attackerPoint2, int attackerNo) {
        boolean f = false;
        if (attackerPoint1 == 5 && attackerPoint2 == 1) {
            f = true;
        } else if (attackerPoint1 == 1 && attackerPoint2 == 5) {
            f = true;
        } else if (attackerPoint1 == 5 && attackerPoint2 == 2) {
            f = true;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 5) {
            f = true;
        } else if (attackerPoint1 == 5 && attackerPoint2 == 5) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 1) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 2) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 3) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 4) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 8) {
            f = true;
        } else if (attackerPoint1 == 1 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 3 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 4 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 8 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 6 && attackerPoint2 == 6) {
            f = true;
        } else if (attackerPoint1 == 2 && attackerPoint2 == 2) {
            f = true;
        } else if (attackerPoint1 == 4 && attackerPoint2 == 4) {
            f = true;
        } else if (attackerPoint1 == 0 && attackerPoint2 == 0) {
            f = true;
        } else if (attackerPoint1 == 9 && attackerPoint2 == 9) {
            f = true;
        } else if (attackerPoint1 == 9 && attackerNo == 1) {
            f = true;
        } else if (attackerPoint2 == 9 && attackerNo == 2) {
            f = true;
        } else if (attackerPoint1 == 7 && attackerPoint2 == 7) {
            f = true;
        } else if (attackerPoint1 == 7 && attackerNo == 1) {
            f = true;
        } else if (attackerPoint2 == 7 && attackerNo == 2) {
            f = true;
        }
        return f;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int attackerNo;
        int defenderNo;
        Player player1;
        Player player2;
        System.out.println("Please select:");
        System.out.println("1. Human-machine combat");
        System.out.println("2. Two person mode");
        System.out.println("3. Electronic Douqiu");
        int choice = sc.nextInt();
        while (!(choice == 1 || choice == 2 || choice == 3)) {
            System.out.println("Please re-enter: ");
            choice = sc.nextInt();
        }
        if (choice == 1) {
            player1 = new Player(1, 1, 10);
            player2 = new Player(1, 1, 10);
        } else if (choice == 2) {
            player1 = new Player(1, 1, 10);
            player2 = new Player(1, 1, 10);
        } else {
            System.out.println("Please enter the amount of health you want to fight crickets for:");
            int qq = sc.nextInt();
            player1 = new Player(1, 1, qq);
            player2 = new Player(1, 1, qq);
        }
        Game game = new Game(player1, player2);
        System.out.println(player1.toString(1));
        System.out.println(player2.toString(2));

        while (!game.checkGameOver()) {
            System.out.println("_________Player1 Round_________");
            if (choice == 3) {
                attackerNo = random.nextInt(2) + 1;
                defenderNo = random.nextInt(2) + 1;
                System.out.println(attackerNo + " " + defenderNo);
            } else {
                attackerNo = sc.nextInt();
                defenderNo = sc.nextInt();
                while (!((attackerNo == 1 || attackerNo == 2) && (defenderNo == 1 || defenderNo == 2))) {
                    System.out.println("Please re-enter: ");
                    attackerNo = sc.nextInt();
                    defenderNo = sc.nextInt();
                }
            }
            if (defenderNo == 1) {
                if (player2.point1 == 0) {
                    System.out.println("Cannot touch 0 warning!");
                    System.out.println("Automatically select to touch another!");
                    if (player2.point2 == 0) {
                        System.out.println("You can't touch anything!");
                    } else {
                        player1.collide(player2, attackerNo, 3 - defenderNo);
                        game.processCollide(player1, player2, attackerNo);
                    }
                } else {
                    player1.collide(player2, attackerNo, defenderNo);
                    game.processCollide(player1, player2, attackerNo);
                }
            } else {
                if (player2.point2 == 0) {
                    System.out.println("Cannot touch 0 warning!");
                    System.out.println("Automatically select to touch another!");
                    if (player2.point1 == 0) {
                        System.out.println("You can't touch anything!");
                    } else {
                        player1.collide(player2, attackerNo, 3 - defenderNo);
                        game.processCollide(player1, player2, attackerNo);
                    }
                } else {
                    player1.collide(player2, attackerNo, defenderNo);
                    game.processCollide(player1, player2, attackerNo);
                }
            }
            System.out.println("___________Situation___________");
            System.out.println(player1.toString(1));
            System.out.println(player2.toString(2));
            if (game.checkGameOver()) {
                break;
            }
            System.out.println("_________Player2 Round_________");
            if (choice == 1 || choice == 3) {
//                attackerNo = random.nextInt(2) + 1;
//                defenderNo = random.nextInt(2) + 1;
                double[][] x = new double[3][3];
                for (int i = 1; i <= 2; i++) {
                    for (int j = 1; j <= 2; j++) {
                        int newPoint1 = player2.point1;
                        int newPoint2 = player2.point2;
                        if (i == 1 && j == 1) {
                            newPoint1 = (player2.point1 + player1.point1) % 10;
                        } else if (i == 1 && j == 2) {
                            newPoint1 = (player2.point1 + player1.point2) % 10;
                        } else if (i == 2 && j == 1) {
                            newPoint2 = (player2.point2 + player1.point1) % 10;
                        } else if (i == 2 && j == 2) {
                            newPoint2 = (player2.point2 + player1.point2) % 10;
                        }
                        int p11 = player1.point1, p12 = player1.point2;
                        if (i == 1) {
                            if (newPoint1 == 7 && (p11 == 6 || p12 == 6)) {
                                if (p11 == 6) {
                                    p11 = 1;
                                } else {
                                    p12 = 1;
                                }
                            } else if (p11 == 0 || p12 == 0) {
                                if (isAttack(newPoint1, newPoint2, i)) {
                                    if (p11 == 0) {
                                        p11 = 1;
                                    } else {
                                        p12 = 1;
                                    }
                                }
                            }
                        } else {
                            if (newPoint2 == 7 && (p11 == 6 || p12 == 6)) {
                                if (p11 == 6) {
                                    p11 = 1;
                                } else {
                                    p12 = 1;
                                }
                            } else if (p11 == 0 || p12 == 0) {
                                if (isAttack(newPoint1, newPoint2, i)) {
                                    if (p11 == 0) {
                                        p11 = 1;
                                    } else {
                                        p12 = 1;
                                    }
                                }
                            }
                        }
                        double d1 = attackerProcess((p11 + newPoint1) % 10, p12, newPoint1, newPoint2, 1, 1, player2.hp - Attack((p11 + newPoint1) % 10, p12, newPoint1, newPoint2, 1), player1.hp + Restore((p11 + newPoint1) % 10, p12, newPoint1, newPoint2, 1)),
                                d2 = attackerProcess((p11 + newPoint2) % 10, p12, newPoint1, newPoint2, 1, 2, player2.hp - Attack((p11 + newPoint2) % 10, p12, newPoint1, newPoint2, 1), player1.hp + Restore((p11 + newPoint2) % 10, p12, newPoint1, newPoint2, 1)),
                                d3 = attackerProcess(p11, (p12 + newPoint1) % 10, newPoint1, newPoint2, 2, 1, player2.hp - Attack(p11, (p12 + newPoint1) % 10, newPoint1, newPoint2, 2), player1.hp + Restore(p11, (p12 + newPoint1) % 10, newPoint1, newPoint2, 2)),
                                d4 = attackerProcess(p11, (p12 + newPoint2) % 10, newPoint1, newPoint2, 2, 2, player2.hp - Attack(p11, (p12 + newPoint2) % 10, newPoint1, newPoint2, 2), player1.hp + Restore(p11, (p12 + newPoint2) % 10, newPoint1, newPoint2, 2));
                        x[i][j] = attackerProcess(newPoint1, newPoint2, player1.point1, player1.point2, i, j, player1.hp, player2.hp) -
                                0.9 * Math.max(Math.max(d1, d2), Math.max(d3, d4));
                        System.out.printf("%.2f ", x[i][j]);
                    }
                }
                double xx = Math.max(Math.max(x[1][1], x[1][2]), Math.max(x[2][1], x[2][2]));
                if (xx == x[1][1]) {
                    attackerNo = 1;
                    defenderNo = 1;
                } else if (xx == x[1][2]) {
                    attackerNo = 1;
                    defenderNo = 2;
                } else if (xx == x[2][1]) {
                    attackerNo = 2;
                    defenderNo = 1;
                } else {
                    attackerNo = 2;
                    defenderNo = 2;
                }
                System.out.println();
                System.out.println(attackerNo + " " + defenderNo);
            } else {
                attackerNo = sc.nextInt();
                defenderNo = sc.nextInt();
                while (!((attackerNo == 1 || attackerNo == 2) && (defenderNo == 1 || defenderNo == 2))) {
                    System.out.println("Please re-enter: ");
                    attackerNo = sc.nextInt();
                    defenderNo = sc.nextInt();
                }
            }
            if (defenderNo == 1) {
                if (player1.point1 == 0) {
                    System.out.println("Cannot touch 0 warning!");
                    System.out.println("Automatically select to touch another!");
                    if (player1.point2 == 0) {
                        System.out.println("You can't touch anything!");
                    } else {
                        player2.collide(player1, attackerNo, 3 - defenderNo);
                        game.processCollide(player2, player1, attackerNo);
                    }
                } else {
                    player2.collide(player1, attackerNo, defenderNo);
                    game.processCollide(player2, player1, attackerNo);
                }
            } else {
                if (player1.point2 == 0) {
                    System.out.println("Cannot touch 0 warning!");
                    System.out.println("Automatically select to touch another!");
                    if (player1.point1 == 0) {
                        System.out.println("You can't touch anything!");
                    } else {
                        player2.collide(player1, attackerNo, 3 - defenderNo);
                        game.processCollide(player2, player1, attackerNo);
                    }
                } else {
                    player2.collide(player1, attackerNo, defenderNo);
                    game.processCollide(player2, player1, attackerNo);
                }
            }
            System.out.println("___________Situation___________");
            System.out.println(player1.toString(1));
            System.out.println(player2.toString(2));
        }
        System.out.println("Game Over!");
    }
}