package com.zackehh.floodz.rmc;

import com.zackehh.corba.common.Alert;
import com.zackehh.corba.common.MetaData;
import com.zackehh.corba.common.Reading;
import com.zackehh.corba.common.SensorMeta;
import com.zackehh.corba.lms.LMS;
import com.zackehh.corba.lms.LMSHelper;
import com.zackehh.corba.rmc.RMCHelper;
import com.zackehh.corba.rmc.RMCPOA;
import com.zackehh.floodz.common.Constants;
import com.zackehh.floodz.common.NamingServiceHandler;
import com.zackehh.floodz.common.SQLiteClient;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RMCDriver extends RMCPOA {

    private static final Logger logger = LoggerFactory.getLogger(RMCDriver.class);

    private NamingContextExt nameService;

    private final List<Alert> alerts = new ArrayList<>();
    private final ORB orb;
    private final RMCClient rmcClient;

    @SuppressWarnings("unused")
    RMCDriver(){
        // testing ctor
        this.orb = null;
        this.rmcClient = null;
    }

    public RMCDriver(String[] args, RMCClient rmcClient){
        // Initialise the ORB
        this.orb = ORB.init(args, null);
        this.rmcClient = rmcClient;

        try {
            // Retrieve a name service
            nameService = NamingServiceHandler.register(
                    orb, this, Constants.REGIONAL_MONITORING_CENTRE, RMCHelper.class
            );
            if(nameService == null){
                throw new Exception();
            }
        } catch(Exception e) {
            throw new IllegalStateException("Retrieved name service is null!");
        }

        // Server has loaded up correctly
        logger.info("Remote Monitoring Centre is operational.");
    }

    @Override
    public boolean ping() {
        return true;
    }

    @Override
    public synchronized void cancelAlert(MetaData metaData) {

        int size = alerts.size();

        for(int i = 0; i < size; i++){
            if(alerts.get(i).meta.sensorMeta.zone.equals(metaData.sensorMeta.zone)){
                alerts.remove(i);
                break;
            }
        }

        rmcClient.cancelAlert(metaData);

        logger.info("Removed alert from sensor #{} in {}", metaData.sensorMeta.sensor, metaData.sensorMeta.zone);

    }

    @Override
    public synchronized void receiveAlert(Alert alert) {

        boolean stored = false;

        for(int i = 0, j = alerts.size(); i < j; i++){

            Alert storedAlert = alerts.get(i);

            if(storedAlert.meta.sensorMeta.zone.equals(alert.meta.sensorMeta.zone)){
                alerts.set(i, alert);
                stored = true;
                break;
            }

        }

        if(!stored){
            alerts.add(alert);
        }

        rmcClient.addAlert(alert);

        logger.info("Received alert from sensor #{} in {}", alert.meta.sensorMeta.sensor, alert.meta.sensorMeta.zone);
    }

    @Override
    public boolean registerLMSConnection(String name) {
        logger.info("Successfully received connection from LMS `{}`", name);
        return true;
    }

    @Override
    public Alert[] getAlerts(){
        return alerts.toArray(new Alert[alerts.size()]);
    }

    @Override
    public Alert[] getDistrictState(String distinct) {
        LMS lms;
        try {
            // Retrieve a name service
            lms = LMSHelper.narrow(nameService.resolve_str(distinct));
            if(lms == null){
                throw new Exception();
            }
        } catch(Exception e) {
            logger.error("Unable to find LMS {}!", distinct);
            return new Alert[]{};
        }
        return lms.getCurrentState();
    }

    public ORB getEmbeddedOrb(){
        return this.orb;
    }
}
