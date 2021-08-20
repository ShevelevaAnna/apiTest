package utils.object_utils;

public class UsersObject {
    private final Company company;
    private final Address address;
    private String name;
    private String  username;
    private String email;
    private String phone;
    private String website;
    private int id;

    public UsersObject(int id, String name, String  username, String email, String phone, String website, String nameCompany,
                       String catchPhrase, String bs, String street, String suite, String city, String zipcode,
                       String lat, String lng){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.company = new Company(nameCompany, catchPhrase, bs);
        this.address = new Address(street, suite, city, zipcode, lat, lng);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        UsersObject user = (UsersObject) obj;
        return id == user.id && name.equals(user.name) && username.equals(user.username) && email.equals(user.email) &&
                phone.equals(user.phone) && website.equals(user.website) && company.equals(user.company) &&
                address.equals(user.address);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
        result = prime * result + company.hashCode();
        result = prime * result + address.hashCode();
        return result;
    }
}

class Company{
    private String name;
    private String catchPhrase;
    private String bs;

    Company (String name, String catchPhrase, String bs){
        this.name = name;
        this.bs = bs;
        this.catchPhrase = catchPhrase;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Company company = (Company) obj;
        return name.equals(company.name) && catchPhrase.equals(company.catchPhrase) && bs.equals(company.bs);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((bs == null) ? 0 : bs.hashCode());
        result = prime * result + ((catchPhrase == null) ? 0 : catchPhrase.hashCode());
        return result;
    }
}
class Geo{
    private String lat;
    private String lng;
    Geo (String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Geo geo = (Geo) obj;
        return lat.equals(geo.lat) && lng.equals(geo.lng);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        return result;
    }
}

class Address{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private final Geo geo;

    Address(String street, String suite, String city, String zipcode, String lat, String lng){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = new Geo(lat, lng);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Address address = (Address) obj;
        return street.equals(address.street) && suite.equals(address.suite) && city.equals(address.city) &&
                zipcode.equals(address.zipcode) && geo.equals(address.geo);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((suite == null) ? 0 : suite.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
        result = prime * result + geo.hashCode();
        return result;
    }
}