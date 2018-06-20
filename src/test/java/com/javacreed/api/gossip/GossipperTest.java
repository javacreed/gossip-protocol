package com.javacreed.api.gossip;

import org.junit.Assert;
import org.junit.Test;

public class GossipperTest {

  @Test
  public void hello() {
    /* Starts with two gossipers */
    final Gossipper a = new Gossipper();
    final Gossipper b = new Gossipper();

    /* A and B do not know about each other */
    Assert.assertEquals(0, a.size());
    Assert.assertEquals(0, b.size());

    /* A connects to B and both will know about each other */
    a.hello(b.getConnection());
    Assert.assertEquals(1, a.size());
    Assert.assertEquals(1, b.size());

    /* The third gossiper joins the fray */
    final Gossipper c = new Gossipper();
    Assert.assertEquals(0, c.size());

    /* C connects to A, who in turn will gossip to B. At the end all will come to know about each other */
    c.hello(a.getConnection());
    Assert.assertEquals(2, a.size());
    Assert.assertEquals(2, b.size());
    Assert.assertEquals(2, c.size());
  }

  @Test
  public void goodbye() {
    final Gossipper a = new Gossipper();
    final Gossipper b = new Gossipper();
    final Gossipper c = new Gossipper();

    /* Connect all gossippers */
    a.hello(b.getConnection());
    a.hello(c.getConnection());
    Assert.assertEquals(2, a.size());
    Assert.assertEquals(2, b.size());
    Assert.assertEquals(2, c.size());

    /* Remove one gossiper */
    c.goodbye(a.getConnection());
    Assert.assertEquals(1, a.size());
    Assert.assertEquals(1, b.size());
    Assert.assertEquals(0, c.size());
  }
}
