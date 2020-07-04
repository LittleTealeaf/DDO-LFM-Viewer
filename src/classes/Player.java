package classes;

public class Player {
    private String Name;
    private String Gender;
    private String Race;
    private String Server;
    private ClassLevel[] Classes;



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public ClassLevel[] getClasses() {
        return Classes;
    }

    public void setClasses(ClassLevel[] levels) {
        Classes = levels;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Name);
        if(Classes != null) {
            builder.append(" ");
            for(ClassLevel classLevel : Classes) {
                builder.append(classLevel.getLevel() + " " + classLevel.getName() + ", ");
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public int getTotalLevels() {
        int totalLevels = 0;
        if(Classes == null) {
            return -1;
        }
        for(ClassLevel level : Classes) {
            totalLevels += level.Level;
        }
        return totalLevels;
    }


    public class ClassLevel {
        private String Name;
        private int Level;

        public String getName() {
            return Name;
        }

        public int getLevel() {
            return Level;
        }
    }
}
