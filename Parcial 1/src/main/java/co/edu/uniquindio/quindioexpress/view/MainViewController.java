package co.edu.uniquindio.quindioexpress.view;

import co.edu.uniquindio.quindioexpress.controller.MainController;
import co.edu.uniquindio.quindioexpress.model.Paquete;
import co.edu.uniquindio.quindioexpress.model.Repartidor;
import co.edu.uniquindio.quindioexpress.model.TipoDespacho;

import co.edu.uniquindio.quindioexpress.comparator.PaquetePrioridadComparator;
import co.edu.uniquindio.quindioexpress.comparator.PaquetePesoComparator;
import co.edu.uniquindio.quindioexpress.comparator.PaqueteTiempoComparator;
import co.edu.uniquindio.quindioexpress.comparator.PaqueteDespachoComparator;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Comparator;
import java.util.Map;

public class MainViewController {

    private final MainController controller = new MainController();

    // PAQUETES

    @FXML
    private TableView<Paquete> tablaPaquetes;
    @FXML
    private TableColumn<Paquete, String> colCodigo;
    @FXML
    private TableColumn<Paquete, String> colDestino;
    @FXML
    private TableColumn<Paquete, Double> colPeso;
    @FXML
    private TableColumn<Paquete, Integer> colPrioridad;
    @FXML
    private TableColumn<Paquete, Integer> colTiempo;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtPrioridad;
    @FXML
    private TextField txtTiempo;
    @FXML
    private TextField txtBuscarCodigo;

    @FXML
    private ComboBox<String> cmbMunicipios;
    @FXML
    private ComboBox<String> cmbOrdenamiento;

    // REPARTIDORES

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private TextField txtZona;
    @FXML
    private CheckBox chkDisponible;
    @FXML
    private TextField txtBuscarIdentificacion;

    @FXML
    private TableView<Repartidor> tablaRepartidores;
    @FXML
    private TableColumn<Repartidor, String> colIdentificacion;
    @FXML
    private TableColumn<Repartidor, String> colNombreCompleto;
    @FXML
    private TableColumn<Repartidor, String> colZona;
    @FXML
    private TableColumn<Repartidor, String> colDisponible;

    // DESPACHOS

    @FXML
    private ComboBox<TipoDespacho> cmbTipoDespacho;
    @FXML
    private TextField txtCodigoEntrega;

    // ASIGNACIONES ACTIVAS

    @FXML
    private TableView<Map.Entry<String, String>> tablaAsignaciones;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> colAsignacionCodigo;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> colAsignacionDestino;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> colAsignacionRepartidor;

    // HISTORIAL DE ENTREGAS

    @FXML
    private TableView<Paquete> tablaHistorialEntregas;
    @FXML
    private TableColumn<Paquete, String> colHistorialCodigo;
    @FXML
    private TableColumn<Paquete, String> colHistorialDestino;
    @FXML
    private TableColumn<Paquete, Double> colHistorialPeso;
    @FXML
    private TableColumn<Paquete, Integer> colHistorialPrioridad;
    @FXML
    private TableColumn<Paquete, Integer> colHistorialTiempo;

    // ALGORITMOS

    @FXML
    private ComboBox<String> cmbMunicipioAlgoritmo;
    @FXML
    private TextField txtPrioridadMinima;
    @FXML
    private TextField txtCodigoBinario;

    @FXML
    private Label lblPesoTotal;
    @FXML
    private Label lblConteoPrioridad;
    @FXML
    private Label lblBusquedaBinaria;
    @FXML
    private Label lblPaqueteMayorPeso;

    // INICIALIZACIÓN

    @FXML
    public void initialize() {

        // TABLA DE PAQUETES

        colCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCodigo()));

        colDestino.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDestino()));

        colPeso.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPeso()));

        colPrioridad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrioridad()));

        colTiempo.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTiempoEstimado()));

        tablaPaquetes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colCodigo.setSortable(false);
        colDestino.setSortable(false);
        colPeso.setSortable(false);
        colPrioridad.setSortable(false);
        colTiempo.setSortable(false);

        cmbOrdenamiento.setItems(FXCollections.observableArrayList("Código (ascendente)", "Prioridad (descendente)", "Peso (descendente)", "Tiempo (ascendente)", "Despacho (prioridad, tiempo, código)"));

        // TABLA DE REPARTIDORES

        colIdentificacion.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getIdentificacion()));

        colNombreCompleto.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombreCompleto()));

        colZona.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getZona()));

        colDisponible.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().isDisponible() ? "Sí" : "No"));

        tablaRepartidores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colIdentificacion.setSortable(false);
        colNombreCompleto.setSortable(false);
        colZona.setSortable(false);
        colDisponible.setSortable(false);

        // TABLA DE ASIGNACIONES ACTIVAS

        colAsignacionCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getKey()));

        colAsignacionDestino.setCellValueFactory(data -> {

            Paquete paquete = controller.getByCodigo(data.getValue().getKey());

            return new ReadOnlyStringWrapper(paquete != null ? paquete.getDestino() : "");
        });

        colAsignacionRepartidor.setCellValueFactory(data -> {

            Repartidor repartidor = controller.getRepartidorByIdentificacion(data.getValue().getValue());

            return new ReadOnlyStringWrapper(repartidor != null ? repartidor.getNombreCompleto() : data.getValue().getValue());
        });

        tablaAsignaciones.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        colAsignacionCodigo.setSortable(false);
        colAsignacionDestino.setSortable(false);
        colAsignacionRepartidor.setSortable(false);

        tablaAsignaciones.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionada) -> {

            if (seleccionada != null) {
                txtCodigoEntrega.setText(seleccionada.getKey());
            }
        });

        // TABLA DEL HISTORIAL DE ENTREGAS

        colHistorialCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCodigo()));

        colHistorialDestino.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDestino()));

        colHistorialPeso.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPeso()));

        colHistorialPrioridad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrioridad()));

        colHistorialTiempo.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTiempoEstimado()));

        tablaHistorialEntregas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        // Conservamos el orden real de las entregas.
        colHistorialCodigo.setSortable(false);
        colHistorialDestino.setSortable(false);
        colHistorialPeso.setSortable(false);
        colHistorialPrioridad.setSortable(false);
        colHistorialTiempo.setSortable(false);

        // TIPO DE DESPACHO

        cmbTipoDespacho.setItems(FXCollections.observableArrayList(TipoDespacho.values()));

        // CARGA INICIAL

        getPaquetes();
        getRepartidores();
        getAsignaciones();
        getHistorialEntregas();

    }

    // REGISTRAR PAQUETE

    @FXML
    private void add() {

        try {

            String codigo = txtCodigo.getText().trim();
            String destino = txtDestino.getText().trim();

            double peso = Double.parseDouble(txtPeso.getText().trim());
            int prioridad = Integer.parseInt(txtPrioridad.getText().trim());
            int tiempo = Integer.parseInt(txtTiempo.getText().trim());

            Paquete paquete = new Paquete(codigo, destino, peso, prioridad, tiempo);

            boolean registrado = controller.add(paquete);

            if (registrado) {

                getPaquetes();
                limpiarFormulario();

                mostrarMensaje("Registro exitoso", "El paquete fue registrado correctamente.", Alert.AlertType.INFORMATION);

            } else {

                mostrarMensaje("Registro rechazado", "Ya existe un paquete con ese código.", Alert.AlertType.WARNING);
            }

        } catch (NumberFormatException e) {

            mostrarMensaje("Datos inválidos", "El peso, la prioridad y el tiempo deben ser números válidos.", Alert.AlertType.ERROR);

        } catch (IllegalArgumentException e) {

            mostrarMensaje("Datos inválidos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // BUSCAR PAQUETE POR CÓDIGO

    @FXML
    private void getByCodigo() {

        String codigo = txtBuscarCodigo.getText().trim();

        if (codigo.isEmpty()) {

            mostrarMensaje("Código requerido", "Ingresa el código del paquete que deseas buscar.", Alert.AlertType.WARNING);

            return;
        }

        Paquete paquete = controller.getByCodigo(codigo);

        if (paquete != null) {

            tablaPaquetes.setItems(FXCollections.observableArrayList(paquete));

        } else {

            mostrarMensaje("Paquete no encontrado", "No existe un paquete pendiente con ese código.", Alert.AlertType.INFORMATION);
        }
    }

    // OBTENER PAQUETES PENDIENTES

    @FXML
    private void getPaquetes() {

        tablaPaquetes.setItems(FXCollections.observableArrayList(controller.getPaquetes()));

        // Conservar selecciones actuales
        String municipioSeleccionado = cmbMunicipios.getValue();
        String municipioAlgoritmoSeleccionado = cmbMunicipioAlgoritmo.getValue();

        // Consultar municipios una sola vez
        var municipios = controller.getMunicipios();

        // Actualizar filtro de paquetes
        cmbMunicipios.setItems(FXCollections.observableArrayList(municipios));

        if (municipioSeleccionado != null && municipios.contains(municipioSeleccionado)) {

            cmbMunicipios.setValue(municipioSeleccionado);
        }

        // Actualizar municipios de algoritmos
        cmbMunicipioAlgoritmo.setItems(FXCollections.observableArrayList(municipios));

        if (municipioAlgoritmoSeleccionado != null && municipios.contains(municipioAlgoritmoSeleccionado)) {

            cmbMunicipioAlgoritmo.setValue(municipioAlgoritmoSeleccionado);
        }

        txtBuscarCodigo.clear();
    }

    // FILTRAR POR MUNICIPIO

    @FXML
    private void getPaquetesByDestino() {

        String destino = cmbMunicipios.getValue();

        if (destino == null) {

            mostrarMensaje("Municipio requerido", "Selecciona un municipio.", Alert.AlertType.WARNING);

            return;
        }

        tablaPaquetes.setItems(FXCollections.observableArrayList(controller.getPaquetesByDestino(destino)));
    }

    // ORDENAR PAQUETES

    @FXML
    private void getPaquetesOrdenados() {

        String criterio = cmbOrdenamiento.getValue();

        if (criterio == null) {

            mostrarMensaje("Criterio requerido", "Selecciona un criterio de ordenamiento.", Alert.AlertType.WARNING);

            return;
        }

        Comparator<Paquete> comparator;

        switch (criterio) {

            case "Código (ascendente)":
                comparator = Comparator.naturalOrder();
                break;

            case "Prioridad (descendente)":
                comparator = new PaquetePrioridadComparator();
                break;

            case "Peso (descendente)":
                comparator = new PaquetePesoComparator();
                break;

            case "Tiempo (ascendente)":
                comparator = new PaqueteTiempoComparator();
                break;

            case "Despacho (prioridad, tiempo, código)":
                comparator = new PaqueteDespachoComparator();
                break;

            default:
                return;
        }

        tablaPaquetes.setItems(FXCollections.observableArrayList(controller.getPaquetesOrdenados(comparator)));
    }

    // LIMPIAR FORMULARIO

    private void limpiarFormulario() {

        txtCodigo.clear();
        txtDestino.clear();
        txtPeso.clear();
        txtPrioridad.clear();
        txtTiempo.clear();
    }

    // REGISTRAR REPARTIDOR

    @FXML
    private void addRepartidor() {

        try {

            Repartidor repartidor = new Repartidor(txtIdentificacion.getText().trim(), txtNombreCompleto.getText().trim(), txtZona.getText().trim(), chkDisponible.isSelected());

            boolean registrado = controller.add(repartidor);

            if (registrado) {

                getRepartidores();

                txtIdentificacion.clear();
                txtNombreCompleto.clear();
                txtZona.clear();
                chkDisponible.setSelected(true);

                mostrarMensaje("Registro exitoso", "El repartidor fue registrado correctamente.", Alert.AlertType.INFORMATION);

            } else {

                mostrarMensaje("Registro rechazado", "Ya existe un repartidor con esa identificación.", Alert.AlertType.WARNING);
            }

        } catch (IllegalArgumentException e) {

            mostrarMensaje("Datos inválidos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // BUSCAR REPARTIDOR

    @FXML
    private void getRepartidorByIdentificacion() {

        String identificacion = txtBuscarIdentificacion.getText().trim();

        if (identificacion.isEmpty()) {

            mostrarMensaje("Identificación requerida", "Ingresa la identificación del repartidor.", Alert.AlertType.WARNING);

            return;
        }

        Repartidor repartidor = controller.getRepartidorByIdentificacion(identificacion);

        if (repartidor != null) {

            tablaRepartidores.setItems(FXCollections.observableArrayList(repartidor));

        } else {

            mostrarMensaje("No encontrado", "No existe un repartidor con esa identificación.", Alert.AlertType.INFORMATION);
        }
    }

    // MOSTRAR REPARTIDORES

    @FXML
    private void getRepartidores() {

        tablaRepartidores.setItems(FXCollections.observableArrayList(controller.getRepartidores()));

        tablaRepartidores.refresh();
    }

    // DESPACHAR PAQUETE

    @FXML
    private void dispatchPaquete() {

        TipoDespacho tipoDespacho = cmbTipoDespacho.getValue();

        if (tipoDespacho == null) {

            mostrarMensaje("Tipo requerido", "Selecciona un tipo de despacho.", Alert.AlertType.WARNING);

            return;
        }

        boolean despachado = controller.dispatchPaquete(tipoDespacho);

        if (despachado) {

            getPaquetes();
            getRepartidores();
            getAsignaciones();

            mostrarMensaje("Despacho exitoso", "El paquete fue asignado a un repartidor.", Alert.AlertType.INFORMATION);

        } else {

            mostrarMensaje("Despacho no realizado", "No fue posible asignar un paquete. Verifica los paquetes pendientes y la disponibilidad de repartidores en sus zonas.", Alert.AlertType.WARNING);
        }
    }

    // CONFIRMAR ENTREGA

    @FXML
    private void confirmEntrega() {

        String codigo = txtCodigoEntrega.getText().trim();

        if (codigo.isEmpty()) {

            mostrarMensaje("Código requerido", "Ingresa el código del paquete.", Alert.AlertType.WARNING);

            return;
        }

        boolean entregado = controller.confirmEntrega(codigo);

        if (entregado) {

            getPaquetes();
            getRepartidores();
            getAsignaciones();

            // Actualizar automáticamente el historial.
            getHistorialEntregas();

            txtCodigoEntrega.clear();

            mostrarMensaje("Entrega confirmada", "El paquete fue entregado y el repartidor quedó disponible.", Alert.AlertType.INFORMATION);

        } else {

            mostrarMensaje("Entrega no confirmada", "No existe una asignación activa para ese código.", Alert.AlertType.WARNING);
        }
    }

    // CONSULTAR ASIGNACIONES ACTIVAS

    @FXML
    private void getAsignaciones() {

        tablaAsignaciones.setItems(FXCollections.observableArrayList(controller.getAsignaciones().entrySet()));

        tablaAsignaciones.refresh();
    }

    // CONSULTAR HISTORIAL DE ENTREGAS

    private void getHistorialEntregas() {

        tablaHistorialEntregas.setItems(FXCollections.observableArrayList(controller.getHistorialEntregas()));

        tablaHistorialEntregas.refresh();
    }

    // MOSTRAR MENSAJES

    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // PESO TOTAL POR MUNICIPIO - RECURSIVIDAD

    @FXML
    private void getPesoTotalByMunicipio() {

        String municipio = cmbMunicipioAlgoritmo.getValue();

        if (municipio == null) {
            mostrarMensaje("Municipio requerido", "Selecciona un municipio.", Alert.AlertType.WARNING);
            return;
        }

        double pesoTotal = controller.getPesoTotalByMunicipio(municipio);

        lblPesoTotal.setText(String.format("Peso total en %s: %.2f kg", municipio, pesoTotal));
    }


    // CONTAR PAQUETES POR PRIORIDAD - RECURSIVIDAD
    @FXML
    private void countPaquetesByPrioridad() {
        try {
            int prioridadMinima = Integer.parseInt(txtPrioridadMinima.getText().trim());
            if (prioridadMinima < 1 || prioridadMinima > 5) {
                mostrarMensaje("Prioridad inválida", "La prioridad debe estar entre 1 y 5.", Alert.AlertType.WARNING);
                return;
            }
            int cantidad = controller.countPaquetesByPrioridad(prioridadMinima);
            lblConteoPrioridad.setText("Paquetes con prioridad >= " + prioridadMinima + ": " + cantidad);
        } catch (NumberFormatException e) {
            mostrarMensaje("Datos inválidos", "Ingresa una prioridad numérica entre 1 y 5.", Alert.AlertType.ERROR);
        }
    }

    // BÚSQUEDA BINARIA POR CÓDIGO
    @FXML
    private void binarySearchByCodigo() {
        String codigo = txtCodigoBinario.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje("Código requerido", "Ingresa el código del paquete.", Alert.AlertType.WARNING);
            return;
        }
        Paquete paquete = controller.binarySearchByCodigo(codigo);
        if (paquete != null) {
            lblBusquedaBinaria.setText("Encontrado: " + paquete.getCodigo() + " | Destino: " + paquete.getDestino() + " | Peso: " + paquete.getPeso() + " kg" + " | Prioridad: " + paquete.getPrioridad());
        } else {
            lblBusquedaBinaria.setText("No se encontró un paquete pendiente con el código " + codigo);
        }
    }

    // PAQUETE MÁS PESADO - DIVIDE Y VENCERÁS
    @FXML
    private void getPaqueteMayorPeso() {
        Paquete paquete = controller.getPaqueteMayorPeso();
        if (paquete == null) {
            lblPaqueteMayorPeso.setText("No hay paquetes pendientes.");
            return;
        }
        lblPaqueteMayorPeso.setText("Código: " + paquete.getCodigo() + " | Destino: " + paquete.getDestino() + " | Peso: " + paquete.getPeso() + " kg" + " | Prioridad: " + paquete.getPrioridad());
    }
}
