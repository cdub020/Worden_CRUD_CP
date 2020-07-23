package com.galvanize.crudcheckpoint;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Authentication {
    private boolean authenticated;
    private userout user;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public userout getUser() {
        return user;
    }

    public void setUser(userout showUser) {
        this.user = showUser;
    }

    public static class userout{
        private long id;
        private String email;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
