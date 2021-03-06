/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST QUC.
 *
 * FenixEdu IST QUC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST QUC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST QUC.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package pt.ist.fenixedu.quc.ui.struts.action.gep.inquiries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.ui.struts.action.base.FenixDispatchAction;
import org.fenixedu.bennu.struts.annotations.Forward;
import org.fenixedu.bennu.struts.annotations.Forwards;
import org.fenixedu.bennu.struts.annotations.Mapping;
import org.fenixedu.bennu.struts.portal.EntryPoint;
import org.fenixedu.bennu.struts.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.renderers.utils.RenderUtils;
import pt.ist.fenixedu.quc.dto.DeleteExecutionCourseResultsBean;
import pt.ist.fenixedu.quc.dto.DeleteProfessorshipResultsBean;

/**
 * @author - Ricardo Rodrigues (ricardo.rodrigues@ist.utl.pt)
 * 
 */
@StrutsFunctionality(app = GepInquiriesApp.class, path = "delete-results", titleKey = "link.inquiries.deleteResults")
@Mapping(path = "/deleteInquiryResults", module = "gep")
@Forwards(@Forward(name = "deleteResults", path = "/gep/inquiries/deleteInquiryResults.jsp"))
public class DeleteInquiriesResultsDA extends FenixDispatchAction {

    @EntryPoint
    public ActionForward prepare(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {
        request.setAttribute("deleteExecutionCourseResults", new DeleteExecutionCourseResultsBean());
        request.setAttribute("deleteProfessorshipResults", new DeleteProfessorshipResultsBean());
        return mapping.findForward("deleteResults");
    }

    public ActionForward deleteExecutionCourseResults(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {
        DeleteExecutionCourseResultsBean deleteExecutionCourseResults = getRenderedObject("deleteExecutionCourseResults");
        RenderUtils.invalidateViewState();

        try {
            if (deleteExecutionCourseResults.deleteResults()) {
                request.setAttribute("deleteSuccessful", "true");
            } else {
                request.setAttribute("nothingDeleted", "true");
            }

        } catch (DomainException e) {
            addErrorMessage(request, e.getKey(), e.getKey(), e.getArgs());
        }

        request.setAttribute("deleteExecutionCourseResults", deleteExecutionCourseResults);
        request.setAttribute("deleteProfessorshipResults", new DeleteProfessorshipResultsBean());
        return mapping.findForward("deleteResults");
    }

    public ActionForward deleteTeacherResults(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {
        DeleteProfessorshipResultsBean deleteProfessorshipResults = getRenderedObject("deleteProfessorshipResults");
        RenderUtils.invalidateViewState();

        try {
            if (deleteProfessorshipResults.deleteResults()) {
                request.setAttribute("deleteSuccessful", "true");
            } else {
                request.setAttribute("nothingDeleted", "true");
            }
        } catch (DomainException e) {
            addErrorMessage(request, e.getKey(), e.getKey(), e.getArgs());
        }

        request.setAttribute("deleteExecutionCourseResults", new DeleteExecutionCourseResultsBean());
        request.setAttribute("deleteProfessorshipResults", deleteProfessorshipResults);
        return mapping.findForward("deleteResults");
    }

    public ActionForward deleteAllTeachersResults(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {
        DeleteProfessorshipResultsBean deleteAllProfessorshipResults = getRenderedObject("deleteAllProfessorshipResults");
        RenderUtils.invalidateViewState();

        try {
            if (deleteAllProfessorshipResults.deleteAllTeachersResults()) {
                request.setAttribute("deleteSuccessful", "true");
            } else {
                request.setAttribute("nothingDeleted", "true");
            }
        } catch (DomainException e) {
            addErrorMessage(request, e.getKey(), e.getKey(), e.getArgs());
        }

        request.setAttribute("deleteExecutionCourseResults", new DeleteExecutionCourseResultsBean());
        request.setAttribute("deleteProfessorshipResults", deleteAllProfessorshipResults);
        return mapping.findForward("deleteResults");
    }
}