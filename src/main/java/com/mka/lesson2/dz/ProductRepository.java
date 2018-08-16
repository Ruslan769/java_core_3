package com.mka.lesson2.dz;

import com.sun.istack.internal.NotNull;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository {

    private final String TABLE_NAME = "PRODUCT";
    private final String QUERY_ID = "ID";
    private final String QUERY_PRODUCT_ID = TABLE_NAME + "_ID";
    private final String QUERY_TITLE = "TITLE";
    private final String QUERY_COST = "COST";
    private final String QUERY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
            "(" + QUERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            QUERY_PRODUCT_ID + " INT NOT NULL, " +
            QUERY_TITLE + " TEXT NOT NULL, " +
            QUERY_COST + " INT)";
    private final String QUERY_INSERT = "INSERT INTO " + TABLE_NAME + " (PRODUCT_ID, TITLE, COST) VALUES (?, ?, ?)";
    private final String QUERY_DELETE_ALL_LINE = "DELETE FROM " + TABLE_NAME;
    private final String QUERY_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    private final String QUERY_SELECT_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE TITLE = ? LIMIT 1";
    private final String QUERY_SELECT_COST_RANGE = "SELECT * FROM " + TABLE_NAME + " WHERE COST >= ? AND COST <= ?";
    private final String QUERY_UPDATE_COST = "UPDATE " + TABLE_NAME + " SET COST = ? WHERE ID = ?";

    private Connection conn;
    private Statement st;
    private PreparedStatement pSt;

    public void createTable() {
        try {
            conn = MyConnection.DBConnection();
            st = conn.createStatement();
            st.execute(QUERY_TABLE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearTable() {
        try {
            conn = MyConnection.DBConnection();
            st = conn.createStatement();
            st.execute(QUERY_DELETE_ALL_LINE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertBatch() {
        try {
            conn = MyConnection.DBConnection();
            conn.setAutoCommit(false);

            pSt = conn.prepareStatement(QUERY_INSERT);
            for (int i = 1; i <= 10000; i++) {
                pSt.setInt(1, i);
                pSt.setString(2, "товар" + i);
                pSt.setInt(3, i * 10);
                pSt.addBatch();
            }
            pSt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Product> get() {
        final List<Product> arProduct = new LinkedList<>();

        try {
            conn = MyConnection.DBConnection();
            st = conn.createStatement();
            final ResultSet arTable = st.executeQuery(QUERY_SELECT_ALL);
            while (arTable.next()) {
                final Product product = new Product(arTable.getInt(QUERY_ID),
                        arTable.getInt(QUERY_PRODUCT_ID),
                        arTable.getString(QUERY_TITLE),
                        arTable.getInt(QUERY_COST));
                arProduct.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arProduct;
    }

    @NotNull
    public Product getProduct(final String nameProduct) {
        Product product = null;
        try {
            conn = MyConnection.DBConnection();
            pSt = conn.prepareStatement(QUERY_SELECT_BY_NAME);
            pSt.setString(1, nameProduct);
            final ResultSet arTable = pSt.executeQuery();
            while (arTable.next()) {
                product = new Product(arTable.getInt(QUERY_ID),
                        arTable.getInt(QUERY_PRODUCT_ID),
                        arTable.getString(QUERY_TITLE),
                        arTable.getInt(QUERY_COST));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @NotNull
    public String getCost(final String nameProduct) {
        final StringBuffer strCost = new StringBuffer();
        final Product product = getProduct(nameProduct);
        if (product != null) {
            strCost.append("Цена товара = " + product.getCost());
        } else {
            strCost.append("Такого товара нет");
        }
        return strCost.toString();
    }

    public List<Product> getCostRange(final int first, final int second) {
        final List<Product> arProduct = new LinkedList<>();

        try {
            conn = MyConnection.DBConnection();
            pSt = conn.prepareStatement(QUERY_SELECT_COST_RANGE);
            pSt.setInt(1, first);
            pSt.setInt(2, second);
            final ResultSet arTable = pSt.executeQuery();
            while (arTable.next()) {
                final Product product = new Product(arTable.getInt(QUERY_ID),
                        arTable.getInt(QUERY_PRODUCT_ID),
                        arTable.getString(QUERY_TITLE),
                        arTable.getInt(QUERY_COST));
                arProduct.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arProduct;
    }

    @NotNull
    public void updateCost(final String nameProduct, final int costProduct) {
        final Product product = getProduct(nameProduct);
        if (product == null) return;

        try {
            conn = MyConnection.DBConnection();
            pSt = conn.prepareStatement(QUERY_UPDATE_COST);
            pSt.setInt(1, costProduct);
            pSt.setInt(2, product.getId());
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
