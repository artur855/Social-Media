/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthurzera.website.services;

import com.arthurzera.website.models.Post;

import java.util.List;

public interface IPostService {

    List<Post> findAll();

    List<Post> findLatest5();

    Post findById(Long id);

    Post create(Post post);

    Post edit(Post post);

    void deleteById(Long id);

}
