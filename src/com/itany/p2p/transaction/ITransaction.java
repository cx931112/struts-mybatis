package com.itany.p2p.transaction;

public interface ITransaction
{
    public void beginTransaction();
    
    public void commit();
    
    public void rollback();
}
