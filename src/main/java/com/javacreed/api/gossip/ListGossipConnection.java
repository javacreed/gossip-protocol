package com.javacreed.api.gossip;

import java.util.ArrayList;
import java.util.List;

public class ListGossipConnection implements GossipConnection {

  private final List<GossipListener> listeners = new ArrayList<>();

  public GossipListenerRegistration addListener(final GossipListener listener) {
    listeners.add(listener);
    return () -> {
      listeners.remove(listener);
    };
  }

  @Override
  public void hello(final GossipConnection connection) {
    listeners.forEach(c -> c.onHello(connection));
  }

  @Override
  public void goodbye(GossipConnection connection) throws NullPointerException, IllegalArgumentException {
    listeners.forEach(c -> c.onGoodbye(connection));
  }
}
