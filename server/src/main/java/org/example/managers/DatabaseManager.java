package org.example.managers;

import org.example.recources.Coordinates;
import org.example.recources.Dragon;
import org.example.recources.DragonCave;
import org.example.recources.DragonType;

import java.sql.*;
import java.util.Hashtable;

public class DatabaseManager {
    private static String URL;
    private static String username;
    private static String password;
    private static final String INSERT_DRAGON = "INSERT INTO dragons (id, name, coordinate_X, coordinate_Y, creationDate, age, weight, speaking, type," +
        "cave_depth, cave_number_of_treasures, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_USER = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
    private static final String GET_USER_BY_USERNAME = "SELECT id, username, password_hash FROM users where username = ?";
    private static final String REMOVE_ALL_USER_DRAGONS = "DELETE FROM dragons WHERE owner_id = ?";
    private static final String REMOVE_DRAGON = "DELETE FROM dragons WHERE id = ?";
    private static final String UPDATE_DRAGON_BY_ID = "UPDATE dragons SET name = ?, coordinate_x = ?, coordinate_y = ?, creationdate = ?, age = ?, weight = ?, " +
            "speaking = ?, type = ?, cave_depth = ?, cave_number_of_treasures = ?, owner_id = ? WHERE id = ?";
    private static DatabaseManager instance = null;
    private static Connection connection;

    public DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static void setURL(String URL) {
        DatabaseManager.URL = URL;
    }

    public static void setUsername(String username) {
        DatabaseManager.username = username;
    }

    public static void setPassword(String password) {
        DatabaseManager.password = password;
    }

    public static boolean registrateUser(String username, String password) {
        String passwordHash = PasswordHandler.hashPassword(password);
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + ". Couldn't register user");
            return false;
        }
    }

    public static boolean checkUser(String username, String password) {
        try {
            // Подготовка запроса для поиска пользователя по логину
            PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME);
            getStatement.setString(1, username);
            ResultSet rs = getStatement.executeQuery();

            // Проверяем, найден ли пользователь
            if (rs.next()) {
                // Сравниваем хэш пароля, хранящийся в базе, с хэшем введенного пароля
                String storedPasswordHash = rs.getString("password_hash");
                String enteredPasswordHash = PasswordHandler.hashPassword(password);

                return storedPasswordHash.equals(enteredPasswordHash);
            }

            // Если пользователь не найден, возвращаем false
            return false;
        } catch (Exception e) {
            e.printStackTrace();  // Для отладки ошибки
            return false;
        }
    }

    public static boolean updateDragonById(long id, String username, Dragon dragon) {
        int userId = getUserId(username);
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DRAGON_BY_ID)) {
            statement.setLong(1, id);
            statement.setInt(12, userId);
            insertDragonIntoStatement(dragon, statement);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't update organization. Reason: " + e.getMessage());
            return false;
        }
    }

    public static void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Database connection error");
            System.exit(-1);
        }
    }

    private static void insertDragonIntoStatement(Dragon dragon, PreparedStatement statement) {
        try {
            statement.setString(2, dragon.getName());
            statement.setDouble(3, dragon.getCoordinates().getX());
            statement.setLong(4, dragon.getCoordinates().getY());
            statement.setDate(5, Date.valueOf(dragon.getCreationDate()));
            statement.setLong(6, dragon.getAge());
            statement.setInt(7, dragon.getWeight());
            statement.setBoolean(8, dragon.getSpeaking());
            statement.setObject(9, dragon.getType().name(), java.sql.Types.OTHER);
            statement.setDouble(10, dragon.getCave().getDepth());
            statement.setInt(11, dragon.getCave().getNumberOfTreasures());
        } catch (SQLException e) {
            System.err.println(e.getMessage() + ". Couldn't insert dragon into statement");
        }
    }

    public static int getUserId(String username) {
        try {
            PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME);
            getStatement.setString(1, username);
            ResultSet rs = getStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

//    public static int getOwnerId(String key) {
//        try {
//            PreparedStatement getStatement = connection.prepareStatement(GET_OWNER_BY_KEY);
//            getStatement.setString(1, key);
//            ResultSet rs = getStatement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("owner_id");
//            }
//            return -2;
//        } catch (Exception e) {
//            return -2;
//        }
//    }

    public static boolean insertDragon(Dragon dragon, String username) {
        int id = getUserId(username);
        try (PreparedStatement statement = connection.prepareStatement(INSERT_DRAGON)) {
            statement.setLong(1, dragon.getID());
            insertDragonIntoStatement(dragon, statement);
            statement.setInt(12, id);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage() + ". Couldn't add dragon");
            return false;
        }
    }

    private static Dragon createDragonFromStatement(ResultSet rs) {
        try {
            Dragon dragon = new Dragon();
            dragon.setID((long) rs.getInt("id"));
            dragon.setName(rs.getString("name"));
            dragon.setCoordinates(new Coordinates(rs.getDouble("coordinate_X"), rs.getLong("coordinate_Y")));
            dragon.setCreationDate(rs.getDate("creation_date").toLocalDate());
            dragon.setAge(rs.getLong("age"));
            dragon.setWeight(rs.getInt("weight"));
            dragon.setSpeaking(rs.getBoolean("speaking"));
            dragon.setType(DragonType.valueOf(rs.getString("type")));
            dragon.setCave(new DragonCave(rs.getInt("cave_depth"), rs.getFloat("cave_number_of_treasures")));
            return dragon;
        } catch (SQLException e) {
            e.getSQLState();
            return null;
        }

    }

    public static Hashtable<Long, Dragon> getCollectionFromDatabase() {
        Hashtable<Long, Dragon> dragons = new Hashtable<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM dragons");
            while (rs.next()) {
                try {
                    Dragon dragon = createDragonFromStatement(rs);
                    dragons.put(dragon.getID(), dragon);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + ". Couldn't load data from database");
            System.exit((-1));
        }
        return dragons;
    }

    public static boolean clear(String username) {
        int userId = getUserId(username);
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_ALL_USER_DRAGONS)) {
            statement.setInt(12, userId);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e){
            System.err.println("Couldn't clear user data from DB. Reason: " + e.getMessage());
        }
        return false;
    }

    public static boolean removeOrganizationById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_DRAGON)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't remove organization. Reason: " + e.getMessage());
            return false;
        }
    }
}
