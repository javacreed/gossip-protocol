package com.javacreed.api.gossip;

public interface GossipConnection {

  void hello(final GossipConnection connection) throws NullPointerException, IllegalArgumentException;

  void goodbye(final GossipConnection connection) throws NullPointerException, IllegalArgumentException;

}
