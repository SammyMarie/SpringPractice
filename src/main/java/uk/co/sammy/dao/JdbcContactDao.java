package uk.co.sammy.dao;

/**
 * Created by smlif on 05/12/2015.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import uk.co.sammy.config.ContactDao;
import uk.co.sammy.model.Contact;

public class JdbcContactDao implements ContactDao {

    private JdbcTemplate connector;

    public JdbcContactDao(DataSource datasource) {
        connector = new JdbcTemplate(datasource);
    }

    @Override
    public void saveOrUpdate(Contact contacts) {
        if (contacts.getContactID() > 0) {
            //Update
            String updateQuery = "UPDATE contact SET name = ?, email = ?, address = ?, telephone = ? WHERE contactID = ?";
            connector.update(updateQuery, contacts.getName(), contacts.getEmail(), contacts.getAddress(), contacts.getTelephone(), contacts.getContactID());
        } else {
            //Insert
            String insertQuery = "INSERT INTO contact (name, email, address, telephone) VALUES (?, ?, ?, ?)";
            connector.update(insertQuery, contacts.getName(), contacts.getEmail(), contacts.getAddress(), contacts.getTelephone());
        }
    }

    @Override
    public void delete(int contactID) {
        String deleteQuery = "DELETE FROM contact WHERE contactID = ?";
        connector.update(deleteQuery, contactID);
    }

    @Override
    public Contact selectById(int contactID) {
        String idSelectQuery = "SELECT * FROM contact WHERE contactID = " + contactID;
        return connector.query(idSelectQuery, new ResultSetExtractor<Contact>() {

            @Override
            public Contact extractData(ResultSet results) throws SQLException, DataAccessException {
                if (results.next()) {
                    int contactID = results.getInt("contactID");
                    String names = results.getString("name");
                    String emails = results.getString("email");
                    String addresses = results.getString("address");
                    String telephoneNumbers = results.getString("telephone");

                    Contact contacts = new Contact(contactID, names, emails, addresses, telephoneNumbers);
                    return contacts;
                }
                return null;
            }
        });
    }

    @Override
    public List<Contact> contactList() {
        String selectQuery = "SELECT * FROM contact";
        List<Contact> contactsList = connector.query(selectQuery, new RowMapper<Contact>() {

            @Override
            public Contact mapRow(ResultSet results, int rowNum) throws SQLException {
                int ids = results.getInt("contactID");
                String names = results.getString("name");
                String emails = results.getString("email");
                String addresses = results.getString("address");
                String telephoneNumbers = results.getString("telephone");

                Contact contacts = new Contact(ids, names, emails, addresses, telephoneNumbers);
                return contacts;
            }
        });
        return contactsList;
    }
}