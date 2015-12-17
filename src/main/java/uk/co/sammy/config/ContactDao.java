package uk.co.sammy.config;

import uk.co.sammy.model.Contact;
import java.util.List;

/**
 * Created by smlif on 05/12/2015.
 */
public interface ContactDao {
    public void saveOrUpdate(Contact contact);
    public void delete(int contactID);
    public Contact selectById(int contactID);
    public List<Contact> contactList();
}
