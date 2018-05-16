package org.openpaas.ieda.controller.iaasMgnt.azureMgnt.web.keypair;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.openpaas.ieda.azureMgnt.web.keypair.dao.AzureKeypairMgntVO;
import org.openpaas.ieda.azureMgnt.web.keypair.service.AzureKeypairMgntService;
import org.openpaas.ieda.controller.iaasMgnt.azureMgnt.web.storageAccount.AzureStorageAccountMgntController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AzureKeypairMgntController {
	@Autowired 
	private AzureKeypairMgntService azureAzureKeypairMgntService;
	
	//@Autowired
    //private MessageSource message;
    private final static Logger LOG = LoggerFactory.getLogger(AzureStorageAccountMgntController.class);
    /***************************************************
    * @project : AZURE 인프라 관리 대시보드
    * @description : AZURE Keypair 관리 화면 이동
    * @title : goAzureKeypairMgnt
    * @return : String
    ***************************************************/
    @RequestMapping(value="/azureMgnt/keypair", method=RequestMethod.GET)
    public String goAzureKeypairMgnt(){
        if (LOG.isInfoEnabled()) {
            LOG.info("================================================> AZURE Keypair 관리 화면 이동");
        }
        return "iaas/azure/keypair/azureKeypairMgnt";
    }
    
    /***************************************************
     * @project : AZURE 관리 대시보드
     * @description : AZURE Keypair 목록 조회
     * @title : getAzurePublicIpInfoList
     * @return : ResponseEntity<?>
     ***************************************************/
    @RequestMapping(value="/azureMgnt/keypair/list/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAzurePublicIpInfoList(Principal principal, @PathVariable("accountId") int accountId){
        List<AzureKeypairMgntVO> list = azureAzureKeypairMgntService.getAzureKeypairList(principal,accountId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(list != null && list.size() != 0){
            map.put("total", list.size());
            map.put("records", list);
        }
        return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
    }
    
}
