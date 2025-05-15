package com.controller.JNLP;

import java.security.cert.X509Certificate;

public class MyCertificate implements java.io.Serializable 
{ 
   	X509Certificate mycert; 
      
    // Default constructor     
    public MyCertificate(X509Certificate mycert) 
    { 
        this.mycert = mycert; 
    } 
  
}
