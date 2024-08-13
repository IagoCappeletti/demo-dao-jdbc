package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection connection;

    public DepartmentDaoJDBC(Connection conn) {
        this.connection = conn;
    }
    @Override
    public void insert(Department dep) {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, dep.getName());

            int rowsAffect = ps.executeUpdate();

            if (rowsAffect > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    dep.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("No rows affected!");
            }

        }catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department dep) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update department set Name = ? where Id = ?");
            ps.setString(1, dep.getName());
            ps.setInt(2, dep.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
