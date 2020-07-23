package com.galvanize.crudcheckpoint;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<User> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getuserbyid(@PathVariable long id){
        return this.repository.findById(id);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        return this.repository.save(user);
    }

    @PostMapping("/authenticate")
    public Authentication authenticate(@RequestBody User theuser) throws NullPointerException{
        Authentication newauth = new Authentication();
        Authentication.userout authuser = new Authentication.userout();
        User tmpuser = this.repository.findByEmail(theuser.getEmail());
        try{
        if (tmpuser.getPassword().equals(theuser.getPassword())){
            newauth.setAuthenticated(true);
            authuser.setEmail(tmpuser.getEmail());
            authuser.setId(tmpuser.getId());
            newauth.setUser(authuser);
        }
        else{
            newauth.setAuthenticated(false);
        }}
        catch (NullPointerException e){
            throw new NullPointerException("Error, must include email and password!");
        }
        return newauth;
    }

    @PatchMapping("/{id}")
    public User patchuser(@PathVariable long id, @RequestBody User thebody){
        Optional <User> theuser = this.repository.findById(id);
        if (thebody.getEmail() != null) {theuser.get().setEmail(thebody.getEmail());}
        if (thebody.getPassword() != null) {theuser.get().setPassword(thebody.getPassword());}
        return this.repository.save(theuser.get());
    }

    @DeleteMapping("/{id}")
    public Count deleteuser(@PathVariable long id){
        this.repository.deleteById(id);
        Count newcount = new Count();
        newcount.setCount(this.repository.count());
        return newcount;
    }


}
