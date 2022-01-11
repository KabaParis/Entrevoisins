package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);



    /**
     * Get all Favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Add Neighbour to Favorite
     * @param neighbour
     */
    void addFavorite(Neighbour neighbour);

    /**
     * Remove Neighbour from Favorite
     * @param neighbour
     */
    void deleteFavorite(Neighbour neighbour);

    /**
     * Get all my Neighbours by Id
     * @return {@link List}
     */
    Neighbour getNeighboursById(long id);

    /**
     * Set Neighbour as favorite or not
     * @return {@link List}
     */
    boolean setFavorite(Neighbour neighbour);


}