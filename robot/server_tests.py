import json
import os
import server
import unittest

import settings


class ServerTestCase(unittest.TestCase):

    def setUp(self):
        server.app.config['TESTING'] = True
        self.app = server.app.test_client()
        self.authenticate('bert', 'test')

    def tearDown(self):
        pass

    def authenticate(self, username=settings.USERNAME,
            password=settings.PASSWORD):
        return self.app.post('/login', data=dict(
            username=username,
            password=password,
        ), follow_redirects=True)

    def test_authentication(self):
        response = self.authenticate('bert', 'test')
        self.assertEqual(response.status, '200 OK')
        self.assertIn('Dashboard', response.data)

        response = self.authenticate('hacker', 'password')
        self.assertEqual(response.status, '200 OK')
        self.assertIn('Invalid', response.data)

    def test_get_configuration(self):
        response = self.app.get('/config')
        data = json.loads(response.data)
        self.assertEqual(data['MAX_SPEED'], 480)

    def test_post_command_go(self):
        response = self.app.post('/command', data={
            'action': 'go',
            'direction': 'forward',
            'time': '1',
        })
        data = json.loads(response.data)
        self.assertEqual(data['status'], 'queued')

        response = self.app.post('/command', data={
            'action': 'go',
            'direction': 'backward',
            'time': '1',
        })
        data = json.loads(response.data)
        self.assertEqual(data['status'], 'queued')

    def test_post_command_turn(self):
        response = self.app.post('/command', data={
            'action': 'turn',
            'direction': 'left',
            'time': '1.2',
        })
        data = json.loads(response.data)
        self.assertEqual(data['status'], 'queued')

        response = self.app.post('/command', data={
            'action': 'turn',
            'direction': 'right',
            'time': '1.1',
        })
        data = json.loads(response.data)
        self.assertEqual(data['status'], 'queued')

if __name__ == '__main__':
    unittest.main()
