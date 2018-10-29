/**
 * 
 */
package br.com.yamaha.sistemagarantia.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbSession;

/**
 * @author Boy & Band
 *
 */
public class GenerateHash {
	
	private UniAddress domain;
    private NtlmPasswordAuthentication authentication;

    /**
     *
     * @param address
     * @param username
     * @param password
     * @throws java.lang.Exception
     */
    public void login(String address, String username, String password) throws Exception
    {

        setDomain(UniAddress.getByName(address));
        setAuthentication(new NtlmPasswordAuthentication(address, username, password));
        SmbSession.logon(getDomain(), authentication);

    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public LinkedList getList(String path) throws Exception
    {
        LinkedList fList = new LinkedList();
        SmbFile f = new SmbFile(path,authentication);
        SmbFile[] fArr = f.listFiles();

        for(int a = 0; a < fArr.length; a++)
        {
            fList.add(fArr[a].getName());
            System.out.println(fArr[a].getName());
        }

        return fList;
    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public boolean checkDirectory(String path) throws Exception
    {
        if(!isExist(path))
        {
            System.out.println(path + " not exist");
            return false;
        }

        if(!isDir(path))
        {
            System.out.println(path + " not a directory");
            return false;
        }

        return true;
    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public boolean isExist(String path) throws Exception
    {
        SmbFile sFile = new SmbFile(path, authentication);

        return sFile.exists();
    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public boolean isDir(String path) throws Exception
    {
        SmbFile sFile = new SmbFile(path, authentication);

        return sFile.isDirectory();
    }

    /**
     *
     * @param path
     * @throws java.lang.Exception
     */
    public void createDir(String path) throws Exception
    {
       SmbFile sFile = new SmbFile(path, authentication);

       sFile.mkdir();
    }

    /**
     *
     * @param path
     * @throws java.lang.Exception
     */
    public void delete(String path) throws Exception
    {
        SmbFile sFile = new SmbFile(path, authentication);
        sFile.delete();
    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public long size(String path) throws Exception
    {
        SmbFile sFile = new SmbFile(path, authentication);

        return sFile.length();
    }

    /**
     *
     * @param path
     * @return
     * @throws java.lang.Exception
     */
    public String getFileName(String path) throws Exception
    {
        SmbFile sFile = new SmbFile(path, authentication);

        return sFile.getName();
    }

    /**
     * @return the domain
     */
    public UniAddress getDomain()
    {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(UniAddress domain)
    {
        this.domain = domain;
    }

    /**
     * @return the authentication
     */
    public NtlmPasswordAuthentication getAuthentication()
    {
        return authentication;
    }

    /**
     * @param authentication the authentication to set
     */
    public void setAuthentication(NtlmPasswordAuthentication authentication)
    {
        this.authentication = authentication;
    }
    
    
	
	public static void main(String[] args) throws Exception {
		
		/*
		try {
			
			//String hash = DigestGenerator.getDigest("edson", DigestGenerator.ALGORITHM_MD5);
			String hash = DigestGenerator.getDigest("GARANTIA", DigestGenerator.ALGORITHM_MD5).toUpperCase();
			System.out.println("Hash:" + hash.toUpperCase());
			 
			String teste = "5432L12345@12346@12347";
			
			String res[] = teste.split("@");
			
			for ( int i = 0 ; i < res.length ; i++)
				System.out.println(res[i]);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		sdf.setLenient(false);	    
    		sdf.parse("99/09/2008");
			
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ParseException pex) {
			
			System.out.println("Data inválida");
		}
		*/
		/*
		GenerateHash gh = new GenerateHash();
		
		gh.login("ymrac.ymdb.com.br","conquest_edson","YmH*02@13");
		
		gh.isDir("images_garantia");
		*/
		try {
            System.out.println(URLDecoder.decode("special chars:ççâéá  ççç", "iso-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
		
		/*
		SmbFile file;
		try {
			file = new SmbFile("smb://ymrac.ymdb.com.br");
			if (file.exists()) {   
	            System.out.println("Existe! " + file.getPath());  
	            String list[] = file.list();
	            
	        } else {
	        	System.out.println("Não Existe!"); 
	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		*/

		
	}

}
