package ec.edu.pucem.formularios;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.dominios.Prefecto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;

public class BocaDeUrna extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Prefecto> prefectos;
	private JPanel panel;
	private JButton btnCancelar;
	private JButton btnGanador;

	public BocaDeUrna(List<Prefecto> prefectos) {
		getContentPane().setBackground(new Color(193, 193, 255));
		this.prefectos = prefectos;
		setTitle("BOCA DE URNA - REGISTRO");
		setBounds(100, 100, 600, 427);
		setClosable(true);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 566, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Votos" }));
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBounds(12, 12, 566, 84);
		getContentPane().add(panel);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(108, 349, 159, 25);
		getContentPane().add(btnCancelar);
		
		// Se añadio el boton de Ganador, para poder saber cual es el ganador, de una forma manual
		btnGanador = new JButton("Ganador");
		btnGanador.setBounds(341, 350, 178, 23);
		btnGanador.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();
	                // La condicional 
	                if (selectedRow != -1) {
	                    JOptionPane.showMessageDialog(null,"El Prefecto ganador es el Candidato numero: " + (selectedRow +1));
	                } else {
	                    JOptionPane.showMessageDialog(null,"Se necesita selecionar una fila para saber el ganador", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
	                }
	            }
	        });
		getContentPane().add(btnGanador);

		model = (DefaultTableModel) table.getModel();
		cargarCandidatos();
		llenarTabla();
	}

	private void cargarCandidatos() {
		// Carga los botones en donde obtiene el nombre de los candidatos y los pone dentro del panel
		int x = 0;
		for (Prefecto prefecto : prefectos) {
			JButton btnPrefecto = new JButton(prefecto.getNombre());
			btnPrefecto.setBounds(x * 155, 0, 150, 80);
			btnPrefecto.addActionListener(this);
			panel.setLayout(null);
			panel.add(btnPrefecto);
			x++;
		}
	}

	private void llenarTabla() {
		model.setRowCount(0);
		// Llena el modelo de la tabla con los nombres y los numeros de votos
		for (Prefecto prefecto : prefectos) {
			Object[] row = new Object[2];
            row[0] = prefecto.getNombre();
            row[1] = prefecto.getVotos();
			model.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		String textoBotonPulsado = e.getActionCommand();
		for (Prefecto prefecto : prefectos) {
			if (textoBotonPulsado == prefecto.getNombre()) {
	            prefecto.setVotos(prefecto.getVotos() + 1);
	            llenarTabla();
            }
		}
	}
	

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}
}
