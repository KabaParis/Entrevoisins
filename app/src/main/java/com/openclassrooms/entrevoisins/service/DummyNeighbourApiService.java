package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    // get favorites from the list

    private List<Neighbour> favorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    /**
     * {@inheritDoc}
     * param neighbour
     */
    @Override
    public void addFavorite(Neighbour neighbour) {
        if (!favorites.contains(neighbour)) {
            favorites.add(neighbour);
        }

    }

    /**
     * {@inheritDoc}
     * param neighbour
     */
    @Override
    public void deleteFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbour)) {
            favorites.remove(neighbour);
        }
    }

    // go through the id list and when it matches, return the neighbour, if not, return null

    /**
     * {@inheritDoc}
     */
    @Override
    public Neighbour getNeighboursById(long id) {
        for (Neighbour neighbour : neighbours)
            if (neighbour.getId() == id) {

                return neighbour;
            }
        return null;
    }


    // set favorite according to its status

    /**
     * {@inheritDoc}
     * param neighbour
     */

    @Override
    public boolean setFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbour)) {
            return true;
        }
        return false;

    }

}



















