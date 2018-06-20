package com.javacreed.api.gossip;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.common.base.Preconditions;

public class Gossipper implements Iterable<GossipConnection> {

  private final ListGossipConnection connection = new ListGossipConnection();

  private final Set<GossipConnection> connections = new HashSet<>();

  public Gossipper() {
    /* TODO: move this to a post construct method */
    connection.addListener(new GossipListener() {
      @Override
      public void onHello(final GossipConnection connection) {
        hello(connection);
      }

      @Override
      public void onGoodbye(GossipConnection connection) {
        goodbye(connection);
      }
    });
  }

  public void goodbye(final GossipConnection connection) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(connection);
    Preconditions.checkArgument(false == connection.equals(this.connection));
    
    if(connections.remove(connection)){
      connection.goodbye(this.connection);
      connections.stream().forEach(c -> c.goodbye(connection));
    }
  }
  
  public void hello(final GossipConnection connection) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(connection);
    Preconditions.checkArgument(false == connection.equals(this.connection));

    /* Add this friend to the list of friends. If we know this friend already, do nothing */
    if (connections.add(connection)) {
      /* Hello back :) */
      connection.hello(this.connection);
      /* Introduce the new friend to all others (skip the same connection) */
      connections.stream().filter(c -> false == c.equals(connection)).forEach(c -> c.hello(connection));
    }
  }

  public GossipConnection getConnection() {
    return connection;
  }

  public int size() {
    return connections.size();
  }

  @Override
  public Iterator<GossipConnection> iterator() {
    return connections.iterator();
  }
}
