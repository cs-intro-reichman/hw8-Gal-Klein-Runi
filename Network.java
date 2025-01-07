/**
 * Represents a social network. The network has users, who follow other uesrs.
 * Each user is an instance of the User class.
 */
public class Network {

    // Fields
    private User[] users; // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /**
     * Creates a network with some users. The only purpose of this constructor is
     * to allow testing the toString and getUser methods, before implementing other
     * methods.
     */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /**
     * Finds in this network, and returns, the user that has the given name.
     * If there is no such user, returns null.
     * Notice that the method receives a String, and returns a User object.
     */
    public User getUser(String name) {
        //// Replace the following statement with your code
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user.getName() == name) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user with the given name to this network.
     * If ths network is full, does nothing and returns false;
     * If the given name is already a user in this network, does nothing and returns
     * false;
     * Otherwise, creates a new user with the given name, adds the user to this
     * network, and returns true.
     */
    public boolean addUser(String name) {
        //// Replace the following statement with your code

        if (userCount < users.length) {
            if (this.getUser(name) == null) {
                users[userCount] = new User(name);
                userCount += 1;
            }
            return true;
        }

        return false;
    }

    /**
     * Makes the user with name1 follow the user with name2. If successful, returns
     * true.
     * If any of the two names is not a user in this network,
     * or if the "follows" addition failed for some reason, returns false.
     */
    public boolean addFollowee(String name1, String name2) {
        //// Replace the following statement with your code
        User user1 = getUser(name1);
        User user2 = getUser(name2);

        if ((user1 != null) & (user2 != null)) {
            return user1.addFollowee(name2);
        }

        return false;
    }

    /**
     * For the user with the given name, recommends another user to follow. The
     * recommended user is
     * the user that has the maximal mutual number of followees as the user with the
     * given name.
     */
    public String recommendWhoToFollow(String name) {
        //// Replace the following statement with your code
        int maxMutual = 0;
        String maxName = "";
        User mainUser = this.getUser(name);
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user.getName() != name) {
                int cMutual = mainUser.countMutual(user);
                if (maxMutual < cMutual) {
                    maxMutual = cMutual;
                    maxName = user.getName();
                }

            }
        }

        return maxName;
    }

    /**
     * Computes and returns the name of the most popular user in this network:
     * The user who appears the most in the follow lists of all the users.
     */
    public String mostPopularUser() {
        int[] usersCount = new int[users.length];

        for (int i = 0; i < users.length; i++) {
            String currentUser = users[i].getName();
            int c = 0;
            for (int j = 0; j < users.length; j++) {
                if (i != j) {
                    String[] userFollowers = users[j].getfFollows();
                    for (int t = 0; t < users[j].getfCount(); t++) {
                        if (userFollowers[t] == currentUser) {
                            c++;
                            break;
                        }
                    }

                }
            }
            usersCount[i] = c;

        }
        int maxIndex = -1;
        int maxC = 0;
        for (int i = 0; i < users.length; i++) {
            if (usersCount[i] > maxC) {
                maxIndex = i;
                maxC = usersCount[i];

            }
        }
        return users[maxIndex].getName();
    }

    /**
     * Returns the number of times that the given name appears in the follows lists
     * of all
     * the users in this network. Note: A name can appear 0 or 1 times in each list.
     */
    private int followeeCount(String name) {
        int c = 0;
        for (int j = 0; j < users.length; j++) {
            if (users[j].getName() != name) {
                String[] userFollowers = users[j].getfFollows();
                for (int t = 0; t < users[j].getfCount(); t++) {
                    if (userFollowers[t] == name) {
                        c++;
                        break;
                    }
                }

            }
        }
        return c; 
}

    // Returns a textual description of all the users in this network, and who they
    // follow.
    public String toString() {
        //// Replace the following statement with your code
        
        String ans = "Network:";
        for (int i = 0; i < userCount; i++) {
            ans = ans + users[i].toString() + " ";
        }
        return ans;
    }
}
