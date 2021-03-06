/**
 * This file was created by Quorum Born IT <http://www.qub-it.com/> and its 
 * copyright terms are bind to the legal agreement regulating the FenixEdu@ULisboa 
 * software development project between Quorum Born IT and Serviços Partilhados da
 * Universidade de Lisboa:
 *  - Copyright © 2015 Quorum Born IT (until any Go-Live phase)
 *  - Copyright © 2015 Universidade de Lisboa (after any Go-Live phase)
 *
 * Contributors: paulo.abrantes@qub-it.com
 *
 * 
 * This file is part of FenixEdu fenixedu-ulisboa-specifications.
 *
 * FenixEdu fenixedu-ulisboa-specifications is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu fenixedu-ulisboa-specifications is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu fenixedu-ulisboa-specifications.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.ulisboa.specifications.service;

import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.academic.domain.student.Student;

public class StudentActive {

    public static boolean isActiveStudent(final Student student) {
        for (final Registration registration : student.getRegistrationsSet()) {
            if (registration.isActive() && !registration.getDegreeType().isEmpty()) {
                return true;
            }
        }

//        final InsuranceEvent event = person.getInsuranceEventFor(executionYear);
//        final PhdIndividualProgramProcess phdIndividualProgramProcess =
//                event != null && event.isClosed() ? find(person.getPhdIndividualProgramProcessesSet()) : null;
//        return (phdIndividualProgramProcess != null);

        return false;
    }

}