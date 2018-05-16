package org.openpaas.ieda.azureMgnt.web.keypair.service;

import java.io.File;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openpaas.ieda.azureMgnt.web.keypair.dao.AzureKeypairMgntVO;
import org.openpaas.ieda.azureMgnt.web.keypair.dto.AzureKeypairMgntDTO;
import org.openpaas.ieda.common.api.LocalDirectoryConfiguration;
import org.openpaas.ieda.common.exception.CommonException;
import org.openpaas.ieda.iaasDashboard.web.account.dao.IaasAccountMgntVO;
import org.openpaas.ieda.iaasDashboard.web.common.service.CommonIaasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

public class AzureKeypairMgntService {
	
	final private static String SSH_DIR = LocalDirectoryConfiguration.getSshDir();
    @Autowired
    private CommonIaasService commonIaasService;
    @Autowired
    private MessageSource message;
	
    /***************************************************
     * @project : AZURE 인프라 관리 대시보드
     * @description : AZURE 계정 정보가 실제 존재 하는지 확인 및 상세 조회
     * @title : getAzureAccountInfo
     * @return : IaasAccountMgntVO
     ***************************************************/
    public IaasAccountMgntVO getAzureAccountInfo(Principal principal, int accountId) {
        return commonIaasService.getIaaSAccountInfo(principal, accountId, "azure");
    }
    
    /***************************************************
     * @project : Azure 관리 대시보드
     * @description : Azure Keypair 목록 조회
     * @title : getAzureKeypairList
     * @return : List<AzurePublicIpMgntVO>
     ***************************************************/
    public List<AzureKeypairMgntVO> getAzureKeypairList(Principal principal, int accountId) {
         //IaasAccountMgntVO vo = getAzureAccountInfo(principal, accountId);
         
         List<String> results = new ArrayList<String>();
         results = getKeypairFileList("azure");
         List<AzureKeypairMgntVO> list = new ArrayList<AzureKeypairMgntVO>();
         for (int i=0; i< results.size(); i++){
        	 String result = results.get(i);
        	 AzureKeypairMgntVO azureVo = new AzureKeypairMgntVO();
             azureVo.setKeypairName(result);
             azureVo.setKeypairType("");
             azureVo.setAccountId(accountId);
             azureVo.setRecid(i);
             list.add(azureVo);
         }
         return list;
    }
    
    
    /***************************************************
     * @project : 인프라 관리 대시보드
     * @description : Azure Public IP 할당
     * @title : createPublicIp
     * @return : void
     ***************************************************/
    public void createKeypair(AzureKeypairMgntDTO dto, Principal principal) {
        //IaasAccountMgntVO vo = getAzureAccountInfo(principal, dto.getAccountId());
        try{
            try {
    			KeyPairGenerator kpg = KeyPairGenerator.getInstance("rsa");
    			kpg.initialize(2048);
    		
             } catch (NoSuchAlgorithmException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }catch (Exception e) {
            String detailMessage = e.getMessage();
            if(!detailMessage.equals("") && detailMessage != "null"){
                throw new CommonException(
                  detailMessage, detailMessage, HttpStatus.BAD_REQUEST);
            }else{
                throw new CommonException(
                  message.getMessage("common.badRequest.exception.code", null, Locale.KOREA), message.getMessage("common.badRequest.message", null, Locale.KOREA), HttpStatus.BAD_REQUEST);
            }
            
        }
    }
    
    /****************************************************************
     * @project :인프라 관리 대시보드
     * @description : 로컬에서 Private Key 파일(.pem)  정보 목록 조회
     * @title : getKeypairFileList
     * @return : List<String>
    *****************************************************************/
    public List<String> getKeypairFileList(String iaasType){
        File keyPathFile = new File(SSH_DIR);
        if ( !keyPathFile.isDirectory() ) {
            return null;
        }
        List<String> localFiles = null;
        File[] listFiles = keyPathFile.listFiles();
        if(listFiles != null){
            for (File file : listFiles) {
                if ( localFiles == null ){
                    localFiles = new ArrayList<String>();
                }
                localFiles.add(file.getName());
            }
        }
        return localFiles;
    }
    
}
