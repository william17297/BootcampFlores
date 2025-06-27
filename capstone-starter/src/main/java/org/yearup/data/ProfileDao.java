package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile getById(int Id);
    boolean update(int userId , String firstName , String lastName
    , String phone , String email , String address ,
                   String city , String state , String zip);
}
