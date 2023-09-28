package helper;

import DAO.AppointmentDao;
import model.Appointment;

import java.sql.SQLException;
import java.time.LocalDateTime;

/** This is the AppointmentHelper.java class. It helps with managing the add and update forms for appointments*/
public class AppointmentHelper {

    /**RUNTIME ERROR corrected by try-catch SQLException.
     * This method shows appointment overlaps.
     * @param newStart the newStart parameter.
     * @param newEnd the newEnd parameter.
     * @param customerId the customerId parameter.
     * @param appId the appointment ID parameter.
     * @return false */
    public static boolean appOverlapCheck(LocalDateTime newStart, LocalDateTime newEnd, int customerId, int appId){
        try {
            for (Appointment app: AppointmentDao.selectAppointmentsByCustomer(customerId)) {
                if (app.getAppointmentId() == appId) {
                    continue;
                }
                if (newStart.isEqual(app.getStart()) || newEnd.isEqual(app.getEnd())){
                    return true;
                }
                if (newStart.isBefore(app.getStart()) && newEnd.isAfter(app.getStart())){
                    return true;
                }
                if (newStart.isAfter(app.getStart()) && newStart.isBefore(app.getEnd())){
                    return true;
                }
                if (newStart.isBefore(app.getStart()) && newEnd.isAfter(app.getEnd())){
                    return true;
                }
                if (newStart.isAfter(app.getStart()) && newEnd.isBefore(app.getEnd())){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

